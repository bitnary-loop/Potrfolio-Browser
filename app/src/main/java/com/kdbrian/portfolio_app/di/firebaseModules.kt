package com.kdbrian.portfolio_app.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.kdbrian.portfolio_app.data.remote.FirebaseAuthRepoImpl
import com.kdbrian.portfolio_app.domain.repo.AuthRepo
import org.koin.dsl.module

val firebaseModules = module{

    single<FirebaseAuth>{ Firebase.auth }

    single<AuthRepo>{
        FirebaseAuthRepoImpl(
            auth = get()
        )
    }

}