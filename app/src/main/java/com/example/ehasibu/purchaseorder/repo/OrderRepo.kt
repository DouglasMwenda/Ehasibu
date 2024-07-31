package com.example.ehasibu.purchaseorder.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.purchaseorder.data.OrderEntity
import com.example.ehasibu.purchaseorder.data.OrderResponse
import com.example.ehasibu.purchaseorder.data.ApproveResponse
import retrofit2.Response

class OrderRepo(private val token: String) {

    private val apiConsumer= AppModule().getRetrofitInstance(token)

   suspend fun getOrders(): Response<OrderResponse<List<OrderEntity>>> {
       return apiConsumer.fetchOrders()
   }
    suspend fun approveOrder(id: String): Response<ApproveResponse> {
       return apiConsumer.approveOrder(id)
   }

}