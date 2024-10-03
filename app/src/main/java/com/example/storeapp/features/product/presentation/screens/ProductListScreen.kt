package com.example.storeapp.features.product.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.storeapp.features.product.presentation.components.ProductCard
import com.example.storeapp.ui.components.bar.CustomTopAppBar
import com.example.storeapp.ui.components.LoadingDialog
import com.example.storeapp.features.product.presentation.states.ProductListViewState
import com.example.storeapp.features.product.presentation.viewmodel.ProductViewModel
import com.example.storeapp.ui.routes.Screen

@Composable
internal fun ProductListScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.stateList.collectAsState()
    ProductListContent(
        state = state,
        navController = navController
    )
}

@Composable
fun ProductListContent(
    state: ProductListViewState,
    navController: NavController
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Products",
                modifier = Modifier
                    .background(Color.Blue),
            )
        }
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(top = it.calculateTopPadding()),
            columns = StaggeredGridCells
                .Fixed(2),
            horizontalArrangement = Arrangement
                .spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            items(state.products) { product ->
                ProductCard(
                    product = product,
                    onClick = {
                        navController
                            .navigate(
                                Screen.ProductDetailScreen.withArgs(
                                    product.id
                                )
                            )
                    }
                )
            }
        }
    }
}