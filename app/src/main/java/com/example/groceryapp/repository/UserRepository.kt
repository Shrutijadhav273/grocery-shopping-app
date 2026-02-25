package com.example.groceryapp.repository

import com.example.groceryapp.data.User
import com.example.groceryapp.data.UserDao

class UserRepository(private val dao: UserDao) {

    suspend fun login(email: String, password: String) = dao.login(email, password)
    suspend fun register(user: User) = dao.register(user)
    suspend fun getUserByEmail(email: String) = dao.getUserByEmail(email)
}