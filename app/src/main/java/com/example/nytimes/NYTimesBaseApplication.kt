package com.example.nytimes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NYTimesBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }


}