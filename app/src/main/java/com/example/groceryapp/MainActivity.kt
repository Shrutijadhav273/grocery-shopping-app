package com.example.groceryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.groceryapp.navigation.NavGraph
import com.example.groceryapp.ui.theme.GroceryAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GroceryAppTheme {
                NavGraph()
            }
        }
    }
}