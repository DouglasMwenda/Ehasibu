package com.example.ehasibu.product.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductFetchRequest
import com.example.ehasibu.product.repo.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = MutableLiveData<List<ProdResponse>>(emptyList())
 private val _product = MutableLiveData<ProdResponse>()
  val product: MutableLiveData<ProdResponse> get() = _product
    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repository.getAllProducts()
                    if (response.isSuccessful) {
                        if (response.body() != null)
                            products.value = response.body()!!.entity

                        delay(10000)
                    }
                } catch (t: Throwable) {
                    Log.e(TAG, "Exception occurred: ${t.message}", t)
                }
            }
        }
    }

    fun fetchProduct(request: ProductFetchRequest) {
        viewModelScope.launch {

            try {

                val response = repository.fetchProduct(request.productName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _product.value = it.entity
                    } ?: {
                        Log.d(TAG, "message: ${response.message()}")
                    }
                } else {
                    Log.d(TAG, "message: ${response.message()}")
                }
            } catch (t: Throwable) {
                Log.e(TAG, "Exception occurred: ${t.message}", t)
            }
        }
    }

}
class ProductProvider(val repo:ProductRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repo) as T
    }

}