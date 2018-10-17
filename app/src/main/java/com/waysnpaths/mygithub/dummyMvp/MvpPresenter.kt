package com.waysnpaths.mygithub.dummyMvp

abstract class MvpPresenter<V : MvpView> {
    protected var view: V? = null
        private set

    fun attachView(view: V) {
        this.view = view
        onAttachView(view)
    }

    open fun onAttachView(view: V) {

    }

    open fun detachView() {
        view = null
        onDetachView()
    }

    open fun onDetachView() {

    }
}