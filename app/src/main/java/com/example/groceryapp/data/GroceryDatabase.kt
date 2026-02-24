package com.shruti.groceryapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.groceryapp.data.Grocery

@Database(entities = [Grocery::class], version = 1)
abstract class GroceryDatabase : RoomDatabase() {

    abstract fun groceryDao(): GroceryDao

    companion object {
        @Volatile
        private var INSTANCE: GroceryDatabase? = null

        fun getDatabase(context: Context): GroceryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GroceryDatabase::class.java,
                    "grocery_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}