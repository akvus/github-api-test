package com.waysnpaths.mygithub.dummyDi

import android.app.Application
import com.waysnpaths.mygithub.data.db.RepositoryDbMapper
import com.waysnpaths.mygithub.data.networking.StubbedNetworking
import com.waysnpaths.mygithub.data.parser.CommitJsonParser
import com.waysnpaths.mygithub.data.parser.ReposJsonParser

object DummyDiModule {
    private lateinit var context: Application

    fun init(context: Application) {
        this.context = context
    }

    fun networking() = StubbedNetworking(context.assets)

    fun reposJsonParser() = ReposJsonParser()
    fun commitsJsonParser() = CommitJsonParser()

    fun repositoryDbMapper() = RepositoryDbMapper()
}