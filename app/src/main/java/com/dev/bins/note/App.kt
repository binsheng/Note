package com.dev.bins.note

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager

/**
 * Created by bin on 11/24/15.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
    }


}
