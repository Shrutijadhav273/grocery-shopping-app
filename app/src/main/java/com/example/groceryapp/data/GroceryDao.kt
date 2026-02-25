package com.example.groceryapp.data

import androidx.room.*

@Dao
interface GroceryDao {

    @Query("SELECT * FROM grocery_table")
    suspend fun getAll(): List<Grocery>

    @Insert
    suspend fun insert(grocery: Grocery)

    @Update
    suspend fun update(grocery: Grocery)

    @Delete
    suspend fun delete(grocery: Grocery)
}