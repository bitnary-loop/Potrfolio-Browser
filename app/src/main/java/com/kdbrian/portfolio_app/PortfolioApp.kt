package com.kdbrian.portfolio_app

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.initialize
import com.kdbrian.portfolio_app.di.authModule
import com.kdbrian.portfolio_app.di.coreModules
import com.kdbrian.portfolio_app.di.firebaseModules
import org.koin.core.context.startKoin
import timber.log.Timber

class PortfolioApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        FirebaseApp.initializeApp(this)

        startKoin {

            modules (
                authModule,
                coreModules,
                firebaseModules,
            )
        }
    }
}