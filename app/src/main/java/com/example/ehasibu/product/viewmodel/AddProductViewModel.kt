package com.example.ehasibu.product.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.repo.ProductRepository
import kotlinx.coroutines.launch

private const val TAG = "addproduct"

class AddProductViewModel(private val repository: ProductRepository) : ViewModel(),
    ViewModelProvider.Factory {
fun  addProduct(product: ProductRequest) {
viewModelScope.launch {

    try {
        val response = repository.addProduct(product)
        if (response.isSuccessful) {
            val message = response.body()?.message

            if (message != null) {
                Log.d(TAG, message)
            }
        } else {
            Log.d(TAG, "Error: ${response.message()}") // Log the error message
        }
    } catch (e: Exception) {
        Log.e(TAG, "Exception occurred: ${e.message}", e) // Log the exception with stack trace
    }
    //  val repository = ProductRepository(token = String())
    //  repository.addProduct(product)
}
}
}