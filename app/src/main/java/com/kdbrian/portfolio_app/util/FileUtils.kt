package com.kdbrian.portfolio_app.util

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns

fun Context.getFileInfo(uri: Uri): FileInfo {
    var fileName: String? = null
    var mimeType: String? = null
    var size: Long? = null
    var width: Int? = null
    var height: Int? = null

    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
        if (cursor.moveToFirst()) {
            fileName = if (nameIndex != -1) cursor.getString(nameIndex) else null
            size = if (sizeIndex != -1) cursor.getLong(sizeIndex) else null
        }
    }

    mimeType = contentResolver.getType(uri)

    if (mimeType?.startsWith("image") == true) {
        contentResolver.openInputStream(uri)?.use { stream ->
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeStream(stream, null, options)
            width = options.outWidth
            height = options.outHeight
        }
    }

    return FileInfo(fileName, mimeType, width, height, size)
}