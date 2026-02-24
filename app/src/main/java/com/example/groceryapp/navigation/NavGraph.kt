package com.example.groceryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.groceryapp.ui.screens.*

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("add") {
            AddEditScreen(navController)
        }

        composable("detail/{itemName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("itemName")
            DetailScreen(navController, name ?: "")
        }
    }
}