package com.example.groceryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.groceryapp.viewmodel.GroceryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GroceryViewModel = viewModel()
) {

    // Observe Room LiveData
    val groceryList by viewModel.allGroceries.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Grocery List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") }
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->

        if (groceryList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No items added yet")
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                items(groceryList) { item ->

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

                            Spacer(modifier = Modifier.height(4.dp))

                            Text("Quantity: ${item.quantity}")
                            Text("₹ ${item.price}")

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
                                    onClick = { viewModel.delete(item) }
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
}