package com.kdbrian.portfolio_app.domain.repo

import com.kdbrian.portfolio_app.BuildConfig
import com.kdbrian.portfolio_app.domain.model.Solution

interface SolutionsRepo {
    val collectionName: String
        get() = "${BuildConfig.APPLICATION_ID}/solutions"
    suspend fun loadSolutions(): Result<List<Solution>>
    suspend fun loadSolution(id: String): Result<Solution>
    suspend fun addSolution(solution: Solution): Result<Boolean>
    suspend fun deleteSolution(id: String): Result<Boolean>
}