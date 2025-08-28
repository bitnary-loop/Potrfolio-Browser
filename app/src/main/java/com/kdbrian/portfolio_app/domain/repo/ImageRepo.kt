package com.kdbrian.portfolio_app.domain.repo

import android.net.Uri

interface ImageRepo {

    suspend fun uploadImage(imageUri: Uri,path: String): Result<String>
    suspend fun deleteImage(imageUri: String): Result<Boolean>

}