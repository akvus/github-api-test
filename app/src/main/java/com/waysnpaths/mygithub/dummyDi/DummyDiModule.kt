package com.waysnpaths.mygithub.dummyDi

import com.waysnpaths.mygithub.data.db.RepositoryDbMapper
import com.waysnpaths.mygithub.data.networking.Networking
import com.waysnpaths.mygithub.data.parser.CommitJsonParser
import com.waysnpaths.mygithub.data.parser.ReposJsonParser

object DummyDiModule {
    fun networking() = Networking()

    fun reposJsonParser() = ReposJsonParser()
    fun commitsJsonParser() = CommitJsonParser()

    fun repositoryDbMapper() = RepositoryDbMapper()
}