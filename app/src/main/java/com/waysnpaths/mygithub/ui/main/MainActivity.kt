package com.waysnpaths.mygithub.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.waysnpaths.mygithub.R
import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.dummyDi.DummyDiModule
import com.waysnpaths.mygithub.dummyMvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<MainPresenter, MainView>(), MainView {
    private var reposAdapter: ReposAdapter? = null

    override fun createPresenter() = MainPresenter(
            DummyDiModule.networking(),
            DummyDiModule.reposJsonParser(),
            DummyDiModule.commitsJsonParser(),
            DummyDiModule.repositoryDbMapper())

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
}
