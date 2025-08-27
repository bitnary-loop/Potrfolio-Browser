package com.kdbrian.portfolio_app.util

data class FileInfo(
    val fileName: String?,
    val mimeType: String?,
    val width: Int? = null,
    val height: Int? = null,
    val size: Long? = null
)