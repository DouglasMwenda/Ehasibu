package com.example.ehasibu.purchaseorder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.purchaseorder.data.OrderEntity
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PurchaseOrderViewModel(private val repo: OrderRepo) : ViewModel() {

    private val _orders = MutableLiveData<List<OrderEntity>>(emptyList())
    val orders: LiveData<List<OrderEntity>> get() = _orders

    private val _order = MutableLiveData<OrderEntity>()
    val order: LiveData<OrderEntity> get() = _order

    init {
        getAllOrders()
    }

    private fun getAllOrders() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getOrders()
                    if (response.isSuccessful) {
                        response.body()?.let { orderResponse ->
                            _orders.value = orderResponse.entity
                        }
                    } else {
                        println("Error: ${response.errorBody()}")
                    }
                    delay(10000)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
        }
    }
}

class OrdersProvider(private val repo: OrderRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PurchaseOrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PurchaseOrderViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
