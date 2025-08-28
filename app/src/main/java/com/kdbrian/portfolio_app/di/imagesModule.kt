package com.kdbrian.portfolio_app.di

import com.kdbrian.portfolio_app.data.remote.FirebaseImageRepo
import com.kdbrian.portfolio_app.domain.repo.ImageRepo
import org.koin.dsl.module

val imagesModule = module {

    single<ImageRepo> {
        FirebaseImageRepo(
            storage = get()
        )
    }

}