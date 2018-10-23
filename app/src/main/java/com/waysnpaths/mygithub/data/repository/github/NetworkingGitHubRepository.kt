package com.waysnpaths.mygithub.data.repository.github

import com.waysnpaths.mygithub.data.networking.Networking
import com.waysnpaths.mygithub.data.parser.CommitJsonParser
import com.waysnpaths.mygithub.data.parser.ReposJsonParser
import com.waysnpaths.mygithub.domain.model.Commit
import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.domain.repository.GitHubRepository

class NetworkingGitHubRepository(
        private val networking: Networking,
        private val reposJsonParser: ReposJsonParser,
        private val commitJsonParser: CommitJsonParser
) : GitHubRepository {
    override fun getRepositories(user: String): List<Repository> {
        return reposJsonParser.parse(networking.get("users/$user/repos"))
    }

    override fun getCommits(user: String, repositoryName: String): List<Commit> {
        return commitJsonParser.parse(networking.get("repos/$user/$repositoryName/commits"))
    }

    override fun saveRepository(repository: Repository) {
        TODO("not implemented")
    }
}