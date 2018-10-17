package com.waysnpaths.mygithub.data.db

import com.waysnpaths.mygithub.domain.ModelMapper
import com.waysnpaths.mygithub.domain.model.Repository

class RepositoryDbMapper : ModelMapper<RepositoryDbModel, Repository> {

    override fun map(from: RepositoryDbModel): Repository {
        return Repository(
                from.id,
                from.name ?: "",
                from.description ?: ""
        )
    }

    override fun mapBack(from: Repository): RepositoryDbModel {
        return RepositoryDbModel().apply {
            id = from.id
            name = from.name
            description = from.description
        }
    }

    override fun mapList(repositoriesFromDb: List<RepositoryDbModel>): MutableList<Repository> {
        val listOfRepositories = mutableListOf<Repository>()
        for (repositoryFromDb in repositoriesFromDb) {
            listOfRepositories.add(map(repositoryFromDb))
        }
        return listOfRepositories
    }

    override fun mapListBack(from: List<Repository>): MutableList<RepositoryDbModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}