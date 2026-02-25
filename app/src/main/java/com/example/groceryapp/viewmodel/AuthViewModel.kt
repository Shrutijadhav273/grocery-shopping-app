package com.example.groceryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.data.AppDatabase
import com.example.groceryapp.data.User
import com.example.groceryapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = UserRepository(AppDatabase.getDatabase(application).userDao())

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repo.login(email, password)
            if (user != null) {
                _currentUser.value = user
            } else {
                _authError.value = "Invalid email or password"
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            val existingUser = repo.getUserByEmail(email)
            if (existingUser == null) {
                repo.register(User(email = email, password = password))
                _authError.value = null
            } else {
                _authError.value = "Email already exists"
            }
        }
    }

    fun logout() {
        _currentUser.value = null
    }

    fun clearError() {
        _authError.value = null
    }
}