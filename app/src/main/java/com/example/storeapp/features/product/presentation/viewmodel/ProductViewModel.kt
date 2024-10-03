package com.example.storeapp.features.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.features.product.domain.repository.ProductRepository
import com.example.storeapp.features.product.presentation.states.ProductDetailViewState
import com.example.storeapp.features.product.presentation.states.ProductListViewState
import com.example.storeapp.ui.sendEvent
import com.example.storeapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _stateList = MutableStateFlow(ProductListViewState())
    private val _stateDetail = MutableStateFlow(ProductDetailViewState())
    val stateList = _stateList.asStateFlow()
    val stateDetail = _stateDetail.asStateFlow()

    init {
        this.getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _stateList.update {
                it.copy(isLoading = true)
            }
            productRepository.fetchProducts()
                .onRight { products ->
                    _stateList.update {
                        it.copy(products = products)
                    }
                }
                .onLeft { error ->
                    _stateList.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            _stateList.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getProduct(id: Int) {
        viewModelScope.launch {
            _stateDetail.update {
                it.copy(isLoading =  true)
            }
            productRepository.fetchProductById(id)
                .onRight { product ->
                    _stateDetail.update {
                        it.copy(product = product)
                    }
                }
                .onLeft { error ->
                    _stateDetail.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            _stateDetail.update {
                it.copy(isLoading = false)
            }
        }
    }
}