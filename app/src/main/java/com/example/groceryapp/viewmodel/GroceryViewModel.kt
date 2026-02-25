package com.example.groceryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.data.Grocery
import com.example.groceryapp.data.GroceryDatabase
import com.example.groceryapp.repository.GroceryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GroceryDatabase.getDatabase(application).groceryDao()
    private val repository = GroceryRepository(dao)

    private val _items = MutableStateFlow<List<Grocery>>(emptyList())
    val items: StateFlow<List<Grocery>> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _items.value = repository.getAllItems()
        }
    }

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            repository.insertItem(Grocery(name = name, quantity = quantity))
            fetchItems()
        }
    }

    fun updateItem(item: Grocery) {
        viewModelScope.launch {
            repository.updateItem(item)
            fetchItems()
        }
    }

    fun deleteItem(item: Grocery) {
        viewModelScope.launch {
            repository.deleteItem(item)
            fetchItems()
        }
    }
}