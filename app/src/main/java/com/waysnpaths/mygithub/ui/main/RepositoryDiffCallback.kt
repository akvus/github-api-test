package com.waysnpaths.mygithub.ui.main

import android.support.v7.util.DiffUtil
import com.waysnpaths.mygithub.domain.model.Repository

class RepositoryDiffCallback(private val oldRepositories: List<Repository>, private val newRepositories: List<Repository>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldRepoPosition: Int, newRepoPosition: Int): Boolean {
        return oldRepositories[oldRepoPosition].id == newRepositories[newRepoPosition].id
    }

    override fun areContentsTheSame(oldRepoPosition: Int, newRepoPosition: Int): Boolean {
        return oldRepositories[oldRepoPosition] == newRepositories[newRepoPosition]
    }

    override fun getOldListSize() = oldRepositories.size

    override fun getNewListSize() = newRepositories.size
}