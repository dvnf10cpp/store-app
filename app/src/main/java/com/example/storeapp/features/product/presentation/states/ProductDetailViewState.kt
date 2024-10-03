package com.example.storeapp.features.product.presentation.states

import com.example.storeapp.features.product.domain.model.Product

data class ProductDetailViewState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    var error: String? = null
)