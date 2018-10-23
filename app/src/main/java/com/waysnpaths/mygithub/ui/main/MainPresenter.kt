package com.waysnpaths.mygithub.ui.main

import android.util.Log
import com.waysnpaths.mygithub.domain.model.Commit
import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.domain.repository.GitHubRepository
import com.waysnpaths.mygithub.dummyMvp.MvpPresenter

class MainPresenter(
        // todo abstract repositories to use cases with executors (ThreatPool etc)
        private val liveGitHubRepository: GitHubRepository,
        private val cacheGitHubRepository: GitHubRepository
) : MvpPresenter<MainView>() {
    var repositories: List<Repository> = listOf()

    override fun onAttachView(view: MainView) {
        getRepositories()
    }

    private fun getRepositories() {
        displayRepositories(getRepositoriesFromDb())
        GetStringRequestAsyncTask({ liveGitHubRepository.getRepositories("akvus") }, ::onRepositoriesJsonReceived, ::onError)
                .execute()
    }

    private fun displayRepositories(repositories: List<Repository>) {
        view?.setRepositories(repositories.sortedBy { it.name })
    }

    private fun getRepositoriesFromDb(): List<Repository> {
        // todo this should be done on another thread
        return cacheGitHubRepository.getRepositories("akvus")
    }

    private fun onRepositoriesJsonReceived(repositories: List<Repository>?) {
        repositories?.let {
            this.repositories = repositories
            storeRepositories(repositories)
            displayRepositories(repositories)
            getCommitsData(repositories)
        }
    }

    private fun storeRepositories(repositories: List<Repository>) {
        for (repository in repositories) {
            // todo bg thread
            cacheGitHubRepository.saveRepository(repository)
        }
    }

    private fun getCommitsData(repositories: List<Repository>) {
        for (repository in repositories) {
            GetStringRequestAsyncTask({ liveGitHubRepository.getCommits("akvus", repository.name) }, {
                onCommitJsonReceived(it, repository.id)
            }, this::onError)
                    .execute()
        }
    }

    private fun onCommitJsonReceived(newCommits: List<Commit>?, repositoryId: Long) {
        repositories = repositories.map { repo ->
            repo.copy().apply {
                commits = if (repo.id == repositoryId) {
                    newCommits ?: listOf()
                } else {
                    commits.map { it.copy() }
                }
            }
        }
        displayRepositories(repositories)
    }

    private fun onError(throwable: Throwable) {
        view?.displayError(throwable.message)
        Log.e(TAG, throwable.message, throwable)
    }

    companion object {
        private val TAG = MainPresenter.javaClass.simpleName
    }
}