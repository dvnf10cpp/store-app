package com.example.storeapp.features.product.domain.repository

import arrow.core.Either
import com.example.storeapp.features.product.domain.NetworkError
import com.example.storeapp.features.product.domain.model.Product

interface ProductRepository {

    suspend fun fetchProducts(): Either<NetworkError, List<Product>>
    suspend fun fetchProductById(id: Int): Either<NetworkError, Product>
}