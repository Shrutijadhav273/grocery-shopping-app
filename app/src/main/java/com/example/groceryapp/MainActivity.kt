package com.example.groceryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.groceryapp.ui.screens.GroceryScreen
import com.example.groceryapp.ui.theme.GroceryAppTheme
import com.example.groceryapp.viewmodel.GroceryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroceryAppTheme {
                val groceryViewModel: GroceryViewModel = viewModel()
                GroceryScreen(viewModel = groceryViewModel)
            }
        }
    }
}