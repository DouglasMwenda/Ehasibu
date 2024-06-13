package com.example.ehasibu.product.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.repo.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = MutableLiveData<List<ProdResponse>>(emptyList())

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
                }catch (t: Throwable){
                    println(t.message)
                }
            }
        }
    }
}

class ProductProvider(val repo:ProductRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repo) as T
    }

}