package com.example.myapplication.navigation.sealedRoutes

import androidx.navigation.NavController

sealed class Screen(val route: String) {
    object Details : Screen("details/{itemId}") {
        // Función para crear la ruta con parámetro
        fun createRoute(itemId: String) = "details/$itemId"

        // Función para navegar con parámetro
        fun navigate(navController: NavController, itemId: String) {
            navController.navigate(createRoute(itemId))
        }
    }

    object Home : Screen("home") {
        // Función para navegar con estilo type-safe
        fun navigate(navController: NavController) {
            navController.navigate(route)
        }
    }


}