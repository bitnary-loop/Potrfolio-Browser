package com.kdbrian.portfolio_app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.kdbrian.portfolio_app.di.authModule
import com.kdbrian.portfolio_app.di.coreModules
import com.kdbrian.portfolio_app.di.createModules
import com.kdbrian.portfolio_app.di.firebaseModules
import com.kdbrian.portfolio_app.di.imagesModule
import com.kdbrian.portfolio_app.di.solutionsModule
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
                createModules,
                firebaseModules,
                imagesModule,
                solutionsModule,
            )
        }
    }
}