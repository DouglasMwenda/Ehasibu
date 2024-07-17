package com.example.ehasibu.productsales.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.productsales.data.SalesEntity
import com.example.ehasibu.productsales.repo.SalesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ProductSalesViewModel(private val repo: SalesRepository) : ViewModel() {
    private val _sales = MutableLiveData<List<SalesEntity>>(emptyList())
    private val sales: MutableLiveData<List<SalesEntity>> get() = _sales

    init {
        getAllSales()
    }

    private fun getAllSales() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getAllSales()
                    if (response.isSuccessful) {
                        response.body()?.let { SalesPResponse ->
                            _sales.value = SalesPResponse.entity
                        }
                    }

                    delay(10000)
                } catch (t: Throwable) {


                }
            }
        }
    }

}

