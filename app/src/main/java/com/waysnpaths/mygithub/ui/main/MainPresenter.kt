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



// deps should be injected
class MainPresenter(
        private val networking: Networking,
        private val reposJsonParser: ReposJsonParser,
        private val commitJsonParser: CommitJsonParser,
        private val repositoryMapper: RepositoryDbMapper
) : MvpPresenter<MainView>() {
    private var repositories = listOf<Repository>()

    override fun onAttachView(view: MainView) {
        getRepositories()
    }

    private fun getRepositories() {
        // todo db call on background thread, when done display and execute the network call in a callback etc.
        val repositories = repositoryMapper.mapList(getRepositoriesFromDb())
        view?.setRepositories(repositories)

        // comment this line to see the db working when run 2nd time
        GetStringRequestAsyncTask(networking, this::onRepositoriesJsonReceived, this::onError)
                .execute("${baseUrl}users/akvus/repos") // todo that shouldn't be executed directly here
        // todo repository patter would play nicely
    }

    private fun getRepositoriesFromDb(): List<RepositoryDbModel> {
        // todo this should be done on another thread!!!
        return SQLite.select().from(RepositoryDbModel::class.java).queryList()
    }

    private fun onRepositoriesJsonReceived(jsonString: String) {
        try {
            repositories = reposJsonParser.parse(jsonString)
            storeRepositories(repositories)
            view?.setRepositories(repositories)
            getCommitsData(repositories)
        } catch (e: JSONException) {
            // todo notify user
        }
    }

    private fun storeRepositories(repositories: List<Repository>) {
        for(repository in repositories) {
            // todo on one had its nice in DbFlow, on the other, it would be good to have it abstracted
            // todo presenter shouldn't know anything about DbFlow, DbFlow should be easily replaceable
            repositoryMapper.mapBack(repository).save()
        }
    }

    private fun getCommitsData(repositories: List<Repository>) {
        for(repository in repositories) {
            GetStringRequestAsyncTask(networking,{
                onCommitJsonReceived(it, repository)
            }, this::onError)
                    .execute("${baseUrl}repos/akvus/${repository.name}/commits") // todo that shouldn't be executed directly here
        }
    }

    private fun onCommitJsonReceived(jsonString: String, repository: Repository) {
        val commits = commitJsonParser.parse(jsonString)
        // todo order commits
        repository.commits.addAll(commits)
        view?.setRepositories(repositories) // todo not a nice solution, but I have not much time left :(
    }

    private fun onError(throwable: Throwable) {
        // todo notify user etc., maybe retry (that could be implemented within a repository pattern)
        Log.e("TAG", "Error", throwable)
    }

    companion object {
        private const val baseUrl = "https://api.github.com/" // todo that shouldn't be here, as well as the calls
    }
}