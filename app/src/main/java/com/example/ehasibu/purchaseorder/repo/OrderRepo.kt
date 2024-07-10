package com.example.ehasibu.purchaseorder.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.purchaseorder.data.OrderResponse
import com.example.ehasibu.purchaseorder.data.OrdersEntity
import retrofit2.Response

class OrderRepo(private val token: String) {
    private val APIConsumer= AppModule().getRetrofitInstance(token)

   suspend fun getOrders(): Response<OrderResponse<OrdersEntity>> {
       return APIConsumer.fetchOrders()
   }

}