package com.example.storeapp.ui.routes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.storeapp.features.product.presentation.screens.ProductDetailScreen
import com.example.storeapp.features.product.presentation.screens.ProductListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ProductListScreen.route
    ) {
        composable(
            route = Screen.ProductListScreen.route
        ) {
            ProductListScreen(
                navController = navController
            )
        }
        composable(
            route = "${Screen.ProductDetailScreen.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0

            ProductDetailScreen(
                navController = navController,
                id = id
            )
        }
    }
}
