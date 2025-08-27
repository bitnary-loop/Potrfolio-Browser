package com.kdbrian.portfolio_app.domain.repo

interface AuthRepo {
    suspend fun emailPasswordSignIn(email: String, password: String): Result<Boolean>
}