package com.example.storeapp.features.product.data.remote

import com.example.storeapp.features.product.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}