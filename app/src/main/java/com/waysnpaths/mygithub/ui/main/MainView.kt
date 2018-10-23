package com.waysnpaths.mygithub.ui.main

import com.waysnpaths.mygithub.domain.model.Repository
import com.waysnpaths.mygithub.dummyMvp.MvpView

interface MainView: MvpView {
    fun setRepositories(repositories: List<Repository>)
    fun displayError(message: String?)
}