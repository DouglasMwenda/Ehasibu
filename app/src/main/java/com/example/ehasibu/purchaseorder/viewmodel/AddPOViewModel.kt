package com.example.ehasibu.purchaseorder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.customerinformation.view.TAG
import com.example.ehasibu.product.repo.ProductRepository
import com.example.ehasibu.purchaseorder.data.PoRequest
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AddPOViewModel(private val repo: OrderRepo, private val productRepo: ProductRepository) :
    ViewModel() {
    private val _isOrderAdded = MutableLiveData<Boolean>()
    val isOrderAdded: LiveData<Boolean> = _isOrderAdded

    private val _products = MutableLiveData<List<String>>()
    val products: LiveData<List<String>> = _products

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = productRepo.getAllProducts()
                    if (response.isSuccessful) {
                        response.body()?.entity?.let { productResponse ->
                            val productNames = productResponse.map { it.productName }
                            _products.postValue(productNames)
                        }
                    } else {
                        Log.d(TAG, "Error: ${response.message()}")
                    }
                } catch (t: Throwable) {
                    Log.e(TAG, "Exception occurred: ${t.message}", t)
                }
            }
        }
    }

    fun addOrder(order: PoRequest) {
        viewModelScope.launch {
            try {
                val response = repo.addOrder(order)
                if (response.isSuccessful) {
                    _isOrderAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.message}", e)
            }
        }
    }
}
class AddPOProvider(private val rep: OrderRepo, private val productRepo: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPOViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddPOViewModel(rep, productRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
