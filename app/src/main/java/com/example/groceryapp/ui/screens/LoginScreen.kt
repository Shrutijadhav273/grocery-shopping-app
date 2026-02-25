package com.example.groceryapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.groceryapp.repository.AuthRepository
import com.example.groceryapp.ui.theme.DarkGreen
import com.example.groceryapp.ui.theme.LightGreen
import com.example.groceryapp.ui.theme.PrimaryGreen

@Composable
fun LoginScreen(navController: NavController) {

    val repo = AuthRepository()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Welcome Back 🛒",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") })

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text("Password") })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                repo.login(email, password,
                    onSuccess = { navController.navigate("home") },
                    onError = {})
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Text("Login")
        }
    }
}