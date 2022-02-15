package com.appsfactory.musicmgmt

import android.app.Application
import com.appsfactory.musicmgmt.common.ApplicationCompositeRoot
import com.appsfactory.musicmgmt.data.remote.network.Api
import com.appsfactory.musicmgmt.data.remote.network.MusicMgmtService

class MyApplication : Application() {

    lateinit var applicationCompositeRoot: ApplicationCompositeRoot

    override fun onCreate() {
        super.onCreate()
        applicationCompositeRoot = ApplicationCompositeRoot(this)
    }
}