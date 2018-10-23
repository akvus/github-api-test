package com.waysnpaths.mygithub

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import com.waysnpaths.mygithub.dummyDi.DummyDiModule

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        DummyDiModule.init(this)

        FlowManager.init(FlowConfig.Builder(this).build())
    }
}