package com.appsfactory.musicmgmt

import android.app.Application
import com.appsfactory.musicmgmt.common.ApplicationCompositeRoot

class MyApplication : Application() {

    lateinit var applicationCompositeRoot: ApplicationCompositeRoot

    override fun onCreate() {
        super.onCreate()
        applicationCompositeRoot = ApplicationCompositeRoot(this)
    }
}