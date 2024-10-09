package com.example.ehasibu.product.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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
}


class ProductProvider(val repo: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repo) as T
    }
}


