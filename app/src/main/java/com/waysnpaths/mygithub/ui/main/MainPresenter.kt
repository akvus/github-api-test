package com.waysnpaths.mygithub.ui.main

import android.util.Log
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.waysnpaths.mygithub.data.db.RepositoryDbMapper
import com.waysnpaths.mygithub.data.db.RepositoryDbModel
import com.waysnpaths.mygithub.data.networking.Networking
import com.waysnpaths.mygithub.data.parser.CommitJsonParser
import com.waysnpaths.mygithub.data.parser.ReposJsonParser
import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.dummyMvp.MvpPresenter
import org.json.JSONException

class MainPresenter(
        private val networking: Networking,
        private val reposJsonParser: ReposJsonParser,
        private val commitJsonParser: CommitJsonParser,
        private val repositoryMapper: RepositoryDbMapper
) : MvpPresenter<MainView>() {

    var repositories: List<Repository> = listOf()

    override fun onAttachView(view: MainView) {
        getRepositories()
    }

    private fun getRepositories() {
        displayRepositories(getRepositoriesFromDb())
        GetStringRequestAsyncTask(networking, this::onRepositoriesJsonReceived, this::onError)
                .execute("${baseUrl}users/akvus/repos")
    }

    private fun displayRepositories(repositories: List<Repository>) {
        view?.setRepositories(repositories.sortedBy { it.name })
    }

    private fun getRepositoriesFromDb(): List<Repository> {
        // todo this should be done on another thread
        return repositoryMapper.mapList(SQLite.select().from(RepositoryDbModel::class.java).queryList())
    }

    private fun onRepositoriesJsonReceived(jsonString: String) {
        try {
            this.repositories = reposJsonParser.parse(jsonString)
            storeRepositories(repositories)
            displayRepositories(repositories)
            getCommitsData(repositories)
        } catch (e: JSONException) {
            onError(e)
        }
    }

    private fun storeRepositories(repositories: List<Repository>) {
        for (repository in repositories) {
            // todo bg thread, abstract save()
            repositoryMapper.mapBack(repository).save()
        }
    }

    private fun getCommitsData(repositories: List<Repository>) {
        for (repository in repositories) {
            GetStringRequestAsyncTask(networking, {
                onCommitJsonReceived(it, repository.id)
            }, this::onError)
                    .execute("${baseUrl}repos/akvus/${repository.name}/commits")
        }
    }

    private fun onCommitJsonReceived(jsonString: String, repositoryId: Long) {
        repositories = repositories.map { repo ->
            repo.copy().apply {
                commits = if (repo.id == repositoryId) {
                    commitJsonParser.parse(jsonString)
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
        private const val baseUrl = "https://api.github.com/"
    }
}