package com.shruti.groceryapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.groceryapp.data.Grocery

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
}