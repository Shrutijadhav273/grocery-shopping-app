package com.example.groceryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class GroceryItem(
    val id: Int,
    val name: String,
    val quantity: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    var itemList by remember {
        mutableStateOf(
            listOf(
                GroceryItem(1, "Rice", 2),
                GroceryItem(2, "Milk", 1),
                GroceryItem(3, "Eggs", 12)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Grocery List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            items(itemList) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text("Quantity: ${item.quantity}")

                        Spacer(modifier = Modifier.height(12.dp))

                        Row {

                            Button(
                                onClick = {
                                    navController.navigate("detail/${item.id}")
                                }
                            ) {
                                Text("View")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    itemList = itemList.filter {
                                        it.id != item.id
                                    }
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}