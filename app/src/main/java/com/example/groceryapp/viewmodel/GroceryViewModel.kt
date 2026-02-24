package com.example.groceryapp.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.groceryapp.data.Grocery
import com.example.groceryapp.repository.GroceryRepository
import com.shruti.groceryapp.data.GroceryDatabase
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GroceryRepository
    val allGroceries: LiveData<List<Grocery>>

    init {
        val dao = GroceryDatabase.getDatabase(application).groceryDao()
        repository = GroceryRepository(dao)
        allGroceries = repository.allGroceries
    }

    fun insert(grocery: Grocery) = viewModelScope.launch {
        repository.insert(grocery)
    }

    fun update(grocery: Grocery) = viewModelScope.launch {
        repository.update(grocery)
    }

    fun delete(grocery: Grocery) = viewModelScope.launch {
        repository.delete(grocery)
    }
}