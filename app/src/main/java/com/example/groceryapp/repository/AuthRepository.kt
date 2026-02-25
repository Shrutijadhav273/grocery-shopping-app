package com.example.groceryapp.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String,
              onSuccess: () -> Unit,
              onError: (String) -> Unit) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Login Failed") }
    }

    fun signup(email: String, password: String,
               onSuccess: () -> Unit,
               onError: (String) -> Unit) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Signup Failed") }
    }
}