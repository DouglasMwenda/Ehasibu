package com.example.ehasibu.productsales.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.productsales.data.AddSaleRequest
import com.example.ehasibu.productsales.data.SalesEntity
import com.example.ehasibu.productsales.repo.SalesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

const val TAG = "sales"

class ProductSalesViewModel(private val repo: SalesRepository) : ViewModel() {
    private val _sales = MutableLiveData<List<SalesEntity>?>(emptyList())
    val sales: MutableLiveData<List<SalesEntity>?> get() = _sales

    private val _isSaleAdded = MutableLiveData<Boolean>()
    val isSaleAdded : LiveData<Boolean> = _isSaleAdded

    init {
        getAllSales()
    }

    private fun getAllSales() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getAllSales()
                    if (response.isSuccessful) {
                        response.body()?.let { salesPResponse ->
                            _sales.value = salesPResponse.entity
                        }
                    }

                    delay(10000)
                } catch (_: Throwable) {


                }
            }
        }
    }

    fun addSale(sale: AddSaleRequest) {
        viewModelScope.launch {
            try {
                val response = repo.createSale(sale)
                if (response.isSuccessful) {
                    _isSaleAdded.postValue(true)
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

class SalesProvider(private val repo: SalesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductSalesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductSalesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

