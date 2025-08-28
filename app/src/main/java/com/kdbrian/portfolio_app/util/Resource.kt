package com.kdbrian.portfolio_app.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String?) : Resource<T>(message = message)
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()

//    fun <R> map(transform: (T) -> R): Resource<R> {
//        return when (this) {
//            is Success -> Success(transform(data))
//            is Error -> Error(message)
//            is Loading -> Loading()
//            is Idle<*> -> Idle()
//        }
//    }
}