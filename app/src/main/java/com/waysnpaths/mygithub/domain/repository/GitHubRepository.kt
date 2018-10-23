package com.waysnpaths.mygithub.domain.repository

import com.waysnpaths.mygithub.domain.model.Commit
import com.waysnpaths.mygithub.domain.model.Repository

interface GitHubRepository {
    fun getRepositories(user: String): List<Repository>
    fun getCommits(user: String, repositoryName: String): List<Commit>
    fun saveRepository(repository: Repository)
}