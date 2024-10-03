package com.example.storeapp.ui.routes

sealed class Screen(
    val route: String
) {
    object ProductListScreen : Screen(
        "product_list_screen"
    )
    object ProductDetailScreen : Screen(
        "product_detail_screen"
    )

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}