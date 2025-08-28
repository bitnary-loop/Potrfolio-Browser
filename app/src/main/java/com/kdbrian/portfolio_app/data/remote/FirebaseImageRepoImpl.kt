package com.kdbrian.portfolio_app.data.remote

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.kdbrian.portfolio_app.domain.repo.ImageRepo
import com.kdbrian.portfolio_app.util.AppError
import kotlinx.coroutines.tasks.await

class FirebaseImageRepo(
    private val storage: FirebaseStorage
) : ImageRepo {

    override suspend fun uploadImage(imageUri: Uri, path: String): Result<String> {
        return try {
            val ref = storage.reference.child(path)
            ref.putFile(imageUri).await()
            val downloadUrl = ref.downloadUrl.await().toString()
            Result.success(downloadUrl)
        } catch (e: Exception) {
            AppError.handleResult(e)
        }
    }

    override suspend fun deleteImage(imageUri: String): Result<Boolean> {
        return try {
            val ref = storage.getReferenceFromUrl(imageUri)
            ref.delete().await()
            Result.success(true)
        } catch (e: Exception) {
            AppError.handleResult(e)
        }
    }
}
