package com.kdbrian.portfolio_app.di

import com.kdbrian.portfolio_app.presentation.ui.state.CreateScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val createModules = module {

    viewModelOf(::CreateScreenViewModel)
}