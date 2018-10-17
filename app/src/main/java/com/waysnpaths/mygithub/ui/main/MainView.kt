package com.waysnpaths.mygithub.ui.main

import com.waysnpaths.mygithub.dummyMvp.MvpView
import com.waysnpaths.mygithub.domain.model.Repository

interface MainView: MvpView {
    fun setRepositories(repositories: List<Repository>)
}