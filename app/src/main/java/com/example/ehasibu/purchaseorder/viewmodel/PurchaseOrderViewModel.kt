package com.example.ehasibu.purchaseorder.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.purchaseorder.data.ApproveResponse
import com.example.ehasibu.purchaseorder.data.OrderEntity
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PurchaseOrderViewModel(private val repo: OrderRepo) : ViewModel() {

    private val _orders = MutableLiveData<List<OrderEntity>>(emptyList())
    val orders: LiveData<List<OrderEntity>> get() = _orders

    private val _order = MutableLiveData<ApproveResponse>()
    val order: LiveData<ApproveResponse> get() = _order


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

    fun approveOrder(id: String) {
        viewModelScope.launch {
            try {
                val response = repo.approveOrder(id)

                if (response.isSuccessful) {
                    _order.postValue(response.body())
                    Log.i(TAG, "approved successfully")
                } else {
                    Log.e(TAG, "Error: ${response.errorBody()?.string()}")
                }

            }
            catch (t: Throwable) {
                Log.e(TAG, "Exception occurred: ${t.message}", t)

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

