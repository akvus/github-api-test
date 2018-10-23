package com.waysnpaths.mygithub.dummyDi

import android.app.Application
import com.waysnpaths.mygithub.data.db.RepositoryDbMapper
import com.waysnpaths.mygithub.data.networking.StubbedNetworking
import com.waysnpaths.mygithub.data.parser.CommitJsonParser
import com.waysnpaths.mygithub.data.parser.ReposJsonParser
import com.waysnpaths.mygithub.data.repository.github.DbGitHubRepository
import com.waysnpaths.mygithub.data.repository.github.NetworkingGitHubRepository

object DummyDiModule {
    private lateinit var context: Application

    fun init(context: Application) {
        this.context = context
    }

    fun networking() = StubbedNetworking(context.assets, "https://api.github.com/")

    fun reposJsonParser() = ReposJsonParser()
    fun commitsJsonParser() = CommitJsonParser()

    fun repositoryDbMapper() = RepositoryDbMapper()

    fun networkingGitHubRepository() = NetworkingGitHubRepository(networking(), reposJsonParser(), commitsJsonParser())
    fun dbGitHubRepository() = DbGitHubRepository(repositoryDbMapper())
}