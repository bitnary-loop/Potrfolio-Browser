package com.kdbrian.portfolio_app.util

object AppError {

    fun handleResult(e: Exception): Result<Nothing> {
        return Result.failure(e)
    }
}