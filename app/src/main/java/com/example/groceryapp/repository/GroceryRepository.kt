package com.example.groceryapp.repository

import com.example.groceryapp.data.Grocery
import com.example.groceryapp.data.GroceryDao

class GroceryRepository(private val dao: GroceryDao) {

    val allGroceries = dao.getAllGroceries()

    suspend fun insert(grocery: Grocery) = dao.insert(grocery)

    suspend fun update(grocery: Grocery) = dao.update(grocery)

    suspend fun delete(grocery: Grocery) = dao.delete(grocery)

    fun getById(id: Int) = dao.getGroceryById(id)
}