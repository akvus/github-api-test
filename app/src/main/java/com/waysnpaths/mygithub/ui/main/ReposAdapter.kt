package com.waysnpaths.mygithub.ui.main

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.waysnpaths.mygithub.R
import com.waysnpaths.mygithub.domain.model.Repository

// would be good to have diffUtil or the new ListAdapter
class ReposAdapter(private val repositories: MutableList<Repository> = mutableListOf())
    : RecyclerView.Adapter<ReposAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val repo = repositories[position]
        holder.apply {
            tvId.text = repo.id.toString()
            tvName.text = repo.name
            tvDescription.text = repo.description
        }
        setLastCommitIfExists(holder, repo)
    }

    private fun setLastCommitIfExists(holder: Holder, repo: Repository) {
        if (repo.commits.size > 0) {
            holder.tvLastCommitDate.visibility = View.VISIBLE
            holder.tvLastCommitLabel.visibility = View.VISIBLE
            holder.tvLastCommitDate.text = repo.commits[0].date
        } else {
            holder.tvLastCommitDate.visibility = View.GONE
            holder.tvLastCommitLabel.visibility = View.GONE
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId = view.findViewById<TextView>(R.id.tvId)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val tvLastCommitLabel = view.findViewById<TextView>(R.id.tvLastCommitLabel)
        val tvLastCommitDate = view.findViewById<TextView>(R.id.tvLastCommitDate)
    }

    fun submitList(newRepositories: List<Repository>) {
        val diffResult = DiffUtil.calculateDiff(RepositoryDiffCallback(repositories, newRepositories))
        repositories.clear()
        repositories.addAll(newRepositories)
        diffResult.dispatchUpdatesTo(this)
    }
}