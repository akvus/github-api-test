package com.waysnpaths.mygithub.data.repository.github

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.waysnpaths.mygithub.data.db.MyDatabase
import com.waysnpaths.mygithub.data.db.RepositoryDbMapper
import com.waysnpaths.mygithub.data.db.RepositoryDbModel
import com.waysnpaths.mygithub.domain.model.Commit
import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.domain.repository.GitHubRepository

class DbGitHubRepository(
        private val repositoryDbMapper: RepositoryDbMapper
) : GitHubRepository {
    override fun getRepositories(user: String): List<Repository> {
        return repositoryDbMapper.mapList(SQLite.select().from(RepositoryDbModel::class.java)
                .queryList())
    }

    override fun getCommits(user: String, repositoryName: String): List<Commit> {
        TODO("not implemented")
    }

    override fun saveRepository(repository: Repository) {
        repositoryDbMapper.mapBack(repository).save()
    }
}