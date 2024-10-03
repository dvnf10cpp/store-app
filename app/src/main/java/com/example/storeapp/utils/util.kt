package com.example.storeapp.utils

fun truncateString(input: String, maxLength: Int = 20): String {
    return if (input.length > maxLength) {
        "${input.take(maxLength - 3)}..."
    } else {
        input
    }
}