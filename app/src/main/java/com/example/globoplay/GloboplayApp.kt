package com.example.globoplay

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GloboplayApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}