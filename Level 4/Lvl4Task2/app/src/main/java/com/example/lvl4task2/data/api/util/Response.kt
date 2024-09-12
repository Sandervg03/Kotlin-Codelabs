package com.example.lvl4task2.data.api.util

sealed class Response<T> (
    val data: Response<T>? = null,
    val message: String? = null
) {
    class Success<T>(data: Response<T>): Response<T>(data)
    class Error<T>(message: String?, data: Response<T>? = null): Response<T>(data, message)
    class Loading<T>: Response<T>()
    class Empty<T>: Response<T>()
}