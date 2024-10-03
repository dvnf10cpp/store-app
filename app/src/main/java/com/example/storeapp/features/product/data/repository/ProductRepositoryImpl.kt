package com.example.storeapp.features.product.data.repository

import arrow.core.Either
import com.example.storeapp.features.product.data.mapper.toNetworkError
import com.example.storeapp.features.product.data.remote.ProductAPI
import com.example.storeapp.features.product.domain.NetworkError
import com.example.storeapp.features.product.domain.model.Product
import com.example.storeapp.features.product.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productAPI: ProductAPI
) : ProductRepository {
    override suspend fun fetchProducts(): Either<NetworkError, List<Product>> {
        return Either.catch {
            productAPI.getProducts()
        }.mapLeft { err ->
            err.toNetworkError()
        }
    }

    override suspend fun fetchProductById(id: Int): Either<NetworkError, Product> {
        return Either.catch {
            productAPI.getProductById(id)
        }.mapLeft { err ->
            err.toNetworkError()
        }
    }
}