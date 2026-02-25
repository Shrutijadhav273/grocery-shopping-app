package com.example.groceryapp.repository

import com.example.groceryapp.data.Grocery
import com.example.groceryapp.data.GroceryDao

class GroceryRepository(private val dao: GroceryDao) {

    suspend fun getAllItems() = dao.getAll()
    suspend fun insertItem(item: Grocery) = dao.insert(item)
    suspend fun updateItem(item: Grocery) = dao.update(item)
    suspend fun deleteItem(item: Grocery) = dao.delete(item)
}