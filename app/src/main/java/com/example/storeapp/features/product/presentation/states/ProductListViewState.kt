package com.example.storeapp.features.product.presentation.states

import com.example.storeapp.features.product.domain.model.Product

data class ProductListViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)