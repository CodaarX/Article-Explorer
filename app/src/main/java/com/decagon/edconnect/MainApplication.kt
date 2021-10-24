package com.decagon.edconnect

import android.app.Application
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.decagon.edconnect.utils.NetworkLiveData
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate(){
        super.onCreate()
        NetworkLiveData.init(this)
    }
}