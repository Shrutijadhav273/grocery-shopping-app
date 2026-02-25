package com.example.groceryapp.data

import androidx.room.*

@Dao
interface GroceryDao {

    @Query("SELECT * FROM grocery_table")
    suspend fun getAll(): List<Grocery>

    @Insert
    suspend fun insert(item: Grocery)

    @Update
    suspend fun update(item: Grocery)

    @Delete
    suspend fun delete(item: Grocery)
}