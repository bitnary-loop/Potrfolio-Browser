package com.kdbrian.portfolio_app

import android.app.Application
import timber.log.Timber

class PortfolioApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}