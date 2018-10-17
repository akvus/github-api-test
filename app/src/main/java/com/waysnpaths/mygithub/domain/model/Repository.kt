package com.waysnpaths.mygithub.domain.model

// this model should not be used in UI, there should be a UI specific model and some mapper
data class Repository(
        var id: Long,
        var name: String,
        var description: String
) {
    val commits = mutableListOf<Commit>()
}