package com.kdbrian.portfolio_app.di

import com.kdbrian.portfolio_app.data.remote.SolutionsRepoImpl
import com.kdbrian.portfolio_app.domain.repo.SolutionsRepo
import org.koin.dsl.module

val solutionsModule = module {

    single<SolutionsRepo> {
        SolutionsRepoImpl(
            auth = get(),
            firestore = get()
        )
    }


}
