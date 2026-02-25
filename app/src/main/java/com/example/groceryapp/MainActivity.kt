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
import com.example.groceryapp.ui.screens.GroceryScreen
import com.example.groceryapp.ui.screens.LoginScreen
import com.example.groceryapp.ui.screens.SignupScreen
import com.example.groceryapp.ui.theme.GroceryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroceryAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel(factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                    val groceryViewModel: GroceryViewModel = viewModel(factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(application))

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { LoginScreen(navController, authViewModel) }
                        composable("signup") { SignupScreen(navController, authViewModel) }
                        composable("grocery") { GroceryScreen(groceryViewModel) }
                    }
                }
            }
        }
    }
}