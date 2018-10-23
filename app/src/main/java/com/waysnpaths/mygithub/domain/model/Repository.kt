package com.waysnpaths.mygithub.domain.model

// this model should not be used in UI, there should be a UI specific model and some mapper
data class Repository(
        var id: Long,
        var name: String,
        var description: String,
        var commits: List<Commit>
) {

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + commits.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Repository

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (commits.size != other.commits.size) return false
        for(i in 0 until commits.size) {
            if (commits[i] != other.commits[i]) return false
        }

        return true
    }
}