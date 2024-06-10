package com.example.ehasibu.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ehasibu.product.data.ProductRequest

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductRequest>>()
    val products: LiveData<List<ProductRequest>> get() = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        // Fetch products from the database and post the values to _products
        val fetchedProducts = listOf(
            ProductRequest("Food", "grain", 2, "flour", "kg"),
            // Add more products fetched from your database
        )
        _products.postValue(fetchedProducts)
    }
}
