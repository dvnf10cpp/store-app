package com.example.storeapp.features.product.domain

data class NetworkError(
    val error: APIError,
    val t: Throwable? = null
)

enum class APIError(
    val message: String
) {
    NetworkError("Network error"),
    UnknownResponse("Unkown Response"),
    UnknownError("Unknown error"),
}