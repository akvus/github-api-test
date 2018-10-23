package com.waysnpaths.mygithub.domain.model

data class Commit(
        var date: String // todo to Date() object
) {
    override fun hashCode(): Int {
        return date.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Commit

        if (date != other.date) return false

        return true
    }
}