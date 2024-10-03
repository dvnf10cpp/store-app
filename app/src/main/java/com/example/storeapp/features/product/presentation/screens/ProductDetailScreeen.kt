package com.example.storeapp.features.product.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.storeapp.features.product.presentation.components.ProductDescription
import com.example.storeapp.features.product.presentation.states.ProductDetailViewState
import com.example.storeapp.features.product.presentation.viewmodel.ProductViewModel
import com.example.storeapp.ui.components.bar.CustomTopAppBar
import com.example.storeapp.ui.components.LoadingDialog
import com.example.storeapp.ui.routes.Screen

@Composable
internal fun ProductDetailScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navController: NavController,
    id: Int
) {
    LaunchedEffect(id) {
        viewModel.getProduct(id)
    }
    val state by viewModel.stateDetail.collectAsState()

    if (state.error != null) {
        ProductDetailError(
            navController= navController
        )
    } else {

        ProductDetailContent(
            state = state,
            navController = navController,
            id = id
        )
    }
}

@Composable
fun ProductDetailContent(
    state: ProductDetailViewState,
    navController: NavController,
    id: Int
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Product Detail $id",
                modifier = Modifier
                    .background(Color.Blue),
                needBackArrow = true,
                onClick = {
                    navController.navigate(Screen.ProductListScreen.route)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Card(
                modifier = Modifier
                    .background(Color.White),

                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults
                    .cardElevation(
                        defaultElevation = 2.dp
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(32.dp),
                ) {
                    AsyncImage(
                        model = state.product?.image,
                        contentDescription = state.product?.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(10.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }

            ProductDescription(
                product = state.product
            )
        }
    }
}

@Composable
fun ProductDetailError(
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Product Detail",
                modifier = Modifier
                    .background(Color.Blue),
                needBackArrow = true,
                onClick = {
                    navController.navigate(Screen.ProductListScreen.route)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Product not found!",
                style = MaterialTheme.typography.bodyLarge
                    .copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    ),
                color = Color.Blue
            )
        }
    }
}