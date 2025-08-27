package com.kdbrian.portfolio_app.util

import android.content.Context
import android.widget.Toast

fun Context.toast(
    message: String,
    length: Int = Toast.LENGTH_SHORT,
    apply: Toast.() -> Unit = {}
) = Toast.makeText(this, message, length).apply(apply).show()