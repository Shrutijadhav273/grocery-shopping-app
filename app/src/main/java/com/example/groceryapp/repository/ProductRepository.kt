package com.example.groceryapp.repository

import com.example.groceryapp.data.Product
import com.google.firebase.database.FirebaseDatabase

class ProductRepository {

    private val database = FirebaseDatabase.getInstance().reference.child("products")

    fun addProduct(product: Product) {
        val id = database.push().key!!
        database.child(id).setValue(product.copy(id = id))
    }

    fun updateProduct(product: Product) {
        database.child(product.id).setValue(product)
    }

    fun deleteProduct(id: String) {
        database.child(id).removeValue()
    }
}