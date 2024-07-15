package com.example.ehasibu.purchaseorder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.purchaseorder.data.OrdersEntity
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PurchaseOrderViewModel(private val repo: OrderRepo) : ViewModel() {
    val orders = MutableLiveData<List<OrdersEntity>?>(emptyList())

    private val _order = MutableLiveData<OrdersEntity>()
    val order: LiveData <OrdersEntity> get() = _order

    init {
        getAllOrders()
    }

    private fun getAllOrders() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getOrders()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            orders.value = it                        }
                    }
                    delay(10000)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
        }
    }
}

class OrdersProvider(val repo: OrderRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PurchaseOrderViewModel(repo) as T
    }
}
