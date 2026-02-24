package com.example.groceryapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao {

    @Insert
    suspend fun insert(grocery: Grocery)

    @Update
    suspend fun update(grocery: Grocery)

    @Delete
    suspend fun delete(grocery: Grocery)

    @Query("SELECT * FROM grocery_table ORDER BY id DESC")
    fun getAllGroceries(): LiveData<List<Grocery>>

    @Query("SELECT * FROM grocery_table WHERE id = :id")
    fun getGroceryById(id: Int): LiveData<Grocery>
}