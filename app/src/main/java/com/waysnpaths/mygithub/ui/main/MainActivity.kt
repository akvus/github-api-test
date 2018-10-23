package com.waysnpaths.mygithub.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.waysnpaths.mygithub.R
import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.dummyDi.DummyDiModule
import com.waysnpaths.mygithub.dummyMvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<MainPresenter, MainView>(), MainView {
    private var reposAdapter: ReposAdapter? = null

    override fun createPresenter() = MainPresenter(
            DummyDiModule.networkingGitHubRepository(),
            DummyDiModule.dbGitHubRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpReposView()
    }

    private fun setUpReposView() {
        rvRepos.layoutManager = LinearLayoutManager(this)

        reposAdapter = ReposAdapter()
        rvRepos.adapter = reposAdapter
    }

    override fun setRepositories(repositories: List<Repository>) {
        reposAdapter?.submitList(repositories)
    }

    override fun displayError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
