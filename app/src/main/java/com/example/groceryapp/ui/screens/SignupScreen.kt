package com.example.groceryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.groceryapp.viewmodel.AuthViewModel

@Composable
fun SignupScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authError by viewModel.authError.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it }, label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password, onValueChange = { password = it }, label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(), visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.signup(email, password)
                navController.navigate("login") { popUpTo("signup") { inclusive = true } }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Sign Up") }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) { Text("Already have account? Login") }

        authError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}