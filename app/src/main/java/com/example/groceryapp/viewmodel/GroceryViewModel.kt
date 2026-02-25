package com.example.groceryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.repository.GroceryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = GroceryRepository(AppDatabase.getDatabase(application).groceryDao())

    private val _items = MutableStateFlow<List<Grocery>>(emptyList())
    val items: StateFlow<List<Grocery>> = _items

    init { fetchItems() }

    private fun fetchItems() {
        viewModelScope.launch {
            _items.value = repo.getAllItems()
        }
    }

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            repo.insertItem(Grocery(name = name, quantity = quantity))
            fetchItems()
        }
    }

    fun updateItem(item: Grocery) {
        viewModelScope.launch {
            repo.updateItem(item)
            fetchItems()
        }
    }

    fun deleteItem(item: Grocery) {
        viewModelScope.launch {
            repo.deleteItem(item)
            fetchItems()
        }
    }
}