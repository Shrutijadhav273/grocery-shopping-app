package com.example.groceryapp.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Login Failed") }
    }

    fun signup(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val userId = result.user?.uid ?: ""

                val userMap = mapOf(
                    "userId" to userId,
                    "name" to name,
                    "email" to email
                )

                database.child("users").child(userId).setValue(userMap)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError("Database Error") }
            }
            .addOnFailureListener { onError(it.message ?: "Signup Failed") }
    }
}