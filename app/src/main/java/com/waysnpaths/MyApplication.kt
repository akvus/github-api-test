package com.waysnpaths

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager


// todo tests!!
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        FlowManager.init(FlowConfig.Builder(this).build())
    }
}