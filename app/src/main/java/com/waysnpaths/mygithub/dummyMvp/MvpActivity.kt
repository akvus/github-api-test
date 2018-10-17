package com.waysnpaths.mygithub.dummyMvp

import android.support.v7.app.AppCompatActivity

abstract class MvpActivity<P: MvpPresenter<V>, V: MvpView>:  AppCompatActivity() {
    protected lateinit var presenter: P

    override fun onStart() {
        super.onStart()
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }


    abstract fun createPresenter() : P
}