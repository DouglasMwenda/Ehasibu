package com.example.ehasibu.purchaseorder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.purchaseorder.data.OrdersEntity
import com.example.ehasibu.purchaseorder.data.OrderResponse
import com.example.ehasibu.purchaseorder.repo.OrderRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class PurchaseOrderViewModel(private val repo: OrderRepo) : ViewModel() {
    val orders = MutableLiveData<OrderResponse<OrdersEntity>>()

    private val _orders= MutableLiveData<OrderResponse<OrdersEntity>>()
    val order: LiveData<OrderResponse<OrdersEntity>> get() = _orders


    init {
        getAllOrders()
    }

    private fun getAllOrders (){
        viewModelScope.launch {
            while (isActive){
                try {
                    val response= repo.getOrders()
                    if(response.isSuccessful){
                         (response.body()?.let {
                             _orders.value= it

                            }
                        )
                    }
                    delay(10000)
                }

                catch (t: Throwable){

                }

            }
        }
    }


}