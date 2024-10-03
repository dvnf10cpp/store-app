package com.example.storeapp.features.product.data.mapper

import com.example.storeapp.features.product.domain.APIError
import com.example.storeapp.features.product.domain.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when(this) {
        is IOException -> APIError.NetworkError
        is HttpException -> APIError.UnknownResponse
        else -> APIError.UnknownError
    }

    return NetworkError(
        error = error,
        t = this
    )
}