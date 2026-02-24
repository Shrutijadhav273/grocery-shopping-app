package com.example.groceryapp.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.groceryapp.data.Grocery
import com.example.groceryapp.viewmodel.GroceryViewModel

@Composable
fun GroceryScreen(viewModel: GroceryViewModel = viewModel()) {

    val groceryList by viewModel.allGroceries.observeAsState(emptyList())

    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("🛒 Grocery App", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                    viewModel.insert(
                        Grocery(
                            name = name,
                            quantity = quantity.toInt(),
                            price = price.toDouble()
                        )
                    )
                    name = ""
                    quantity = ""
                    price = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(groceryList) { grocery ->
                GroceryItem(grocery, onDelete = { viewModel.delete(it) })
            }
        }
    }
}

@Composable
fun GroceryItem(grocery: Grocery, onDelete: (Grocery) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(grocery.name, style = MaterialTheme.typography.titleMedium)
                Text("Qty: ${grocery.quantity}")
                Text("₹ ${grocery.price}")
            }
            Button(onClick = { onDelete(grocery) }) {
                Text("Delete")
            }
        }
    }
}