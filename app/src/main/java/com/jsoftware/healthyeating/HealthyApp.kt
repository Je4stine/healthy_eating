package com.jsoftware.healthyeating

import android.app.Application
import org.koin.core.context.startKoin

class HealthyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { }
    }

}