package com.waysnpaths.mygithub.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.waysnpaths.mygithub.R
import com.waysnpaths.mygithub.domain.model.Repository

// would be good to have diffUtil or the new ListAdapter
class ReposAdapter(private val reposList: MutableList<Repository> = mutableListOf<Repository>())
    : RecyclerView.Adapter<ReposAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val repo = reposList[position]
        holder.tvId.text = repo.id.toString()
        holder.tvName.text = repo.name
        holder.tvDescription.text = repo.description
        setLastCommitIfThereExists(holder, repo)
    }

    private fun setLastCommitIfThereExists(holder: Holder, repo: Repository) {
        if (repo.commits.size > 0) {
            holder.tvLastCommitDate.visibility = View.VISIBLE
            holder.tvLastCommitLabel.visibility = View.VISIBLE
            holder.tvLastCommitDate.text = repo.commits[0].date
            // todo the commits seems to be ordered by date in the API, however, we should not assume that
            // todo the results should be first ordered in presenter (if there would be time :)
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

    fun changeItems(repos: List<Repository>) {
        reposList.clear()
        reposList.addAll(repos)
        notifyDataSetChanged()
    }
}