package com.example.groceryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.ui.screens.HomeScreen
import com.example.groceryapp.ui.screens.LoginScreen
import com.example.groceryapp.ui.screens.SignupScreen
import com.example.groceryapp.ui.theme.GroceryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "login") {

                composable("login") { LoginScreen(navController) }
                composable("home") { HomeScreen(navController) }
                composable("signup") { SignupScreen(navController) }
            }
        }
    }
}