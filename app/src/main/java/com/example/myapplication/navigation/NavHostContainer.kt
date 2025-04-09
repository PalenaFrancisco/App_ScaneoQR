package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.navigation.sealedRoutes.Screen
import com.example.myapplication.ui.details.Details
import com.example.myapplication.ui.home.Home

@Composable
fun NavHostContainer() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { Home(navController) }
        composable(Screen.Details.route, arguments = listOf(navArgument("itemId" ) {type = NavType.StringType})) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            Details(itemId = itemId, navController = navController)
        }
    }

}