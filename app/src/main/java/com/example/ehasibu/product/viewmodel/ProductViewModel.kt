package com.example.ehasibu.product.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.product.data.PriceRequest
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductFetchRequest
import com.example.ehasibu.product.repo.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = MutableLiveData<List<ProdResponse>?>(emptyList())

    private val _product = MutableLiveData<ProdResponse>()
    val product: LiveData<ProdResponse> get() = _product


   // private val _updateProductResponse = MutableLiveData<DelResponse>()
  //  val updateProductResponse: LiveData<DelResponse> = _updateProductResponse


    private val _filteredProducts = MutableLiveData<List<ProdResponse>>()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch() {
            while (isActive) {
                try {
                    val response = repository.getAllProducts()
                    if (response.isSuccessful) {
                        response.body()?.entity?.let {
                            products.value = it
                            _filteredProducts.value =
                                it // Initialize filteredProducts with all products
                        }
                    }
                } catch (t: Throwable) {
                    Log.e(TAG, "Exception occurred: ${t.message}", t)
                }
            }
        }
    }

    fun fetchProduct(request: ProductFetchRequest) {
        viewModelScope.launch() {
            try {
                val response = repository.fetchProduct(request.productName)
                if (response.isSuccessful) {
                    response.body()?.entity?.let {
                        _product.value = it
                    } ?: run {
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

    fun filterProducts(query: String) {
        val currentProducts = products.value ?: emptyList()
        val filteredList = if (query.isEmpty()) {
            currentProducts
        } else {
            currentProducts.filter { it.productName.contains(query, ignoreCase = true) }
        }
        _filteredProducts.postValue(filteredList)
    }

    fun deleteProduct(productId: String) {
        viewModelScope.launch {
            try {
                val response = repository.deleteProduct(productId)
                if (response.isSuccessful) {
                    val updatedProducts = products.value?.filterNot { it.productId == productId }
                    products.value = updatedProducts
                    Log.d(TAG, "message: ${response.message()}")
                } else {
                    Log.d(TAG, "message: ${response.message()}")
                }
            } catch (t: Throwable) {
                Log.e(TAG, "Exception occurred: ${t.message}", t)
            }
        }
    }


    fun setPrice(request: PriceRequest) {
        viewModelScope.launch {
            try {
                val response = repository.setPrice(request)

                if (response.isSuccessful) {
                    val setPriceResponse = response.body()
                    setPriceResponse?.let {
                        products.value
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


class ProductProvider(val repo: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repo) as T
    }
}


