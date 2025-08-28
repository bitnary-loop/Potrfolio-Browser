package com.kdbrian.portfolio_app.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.kdbrian.portfolio_app.domain.repo.AuthRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseAuthRepoImpl(
    private val auth: FirebaseAuth
) : AuthRepo {
    override suspend fun emailPasswordSignIn(
        email: String,
        password: String
    ): Result<Boolean> = withContext(Dispatchers.IO) {

        try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            if (authResult.user == null) {
                Result.failure(Exception("Invalid authentication details."))
            } else
                Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }


    }

    override suspend fun emailPasswordSignUp(
        email: String,
        password: String
    ): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            if (authResult.user == null) {
                Result.failure(Exception("Invalid authentication details."))
            } else
                Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }


    }





}