package com.example.themobilemoviedatabase.data.network.utils

sealed class Resources<out T> (
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : Resources<T>(data)
    class Success<out T>(data: T?) : Resources<T>(data)
    class Error(message: String): Resources<Nothing>(message = message)
}
