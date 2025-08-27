package com.kdbrian.portfolio_app.util

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
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