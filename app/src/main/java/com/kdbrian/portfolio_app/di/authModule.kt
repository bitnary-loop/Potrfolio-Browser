package com.kdbrian.portfolio_app.di

import com.kdbrian.portfolio_app.presentation.ui.state.SignInViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {

    viewModelOf(::SignInViewModel)
}