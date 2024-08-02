package com.example.ehasibu.customerinformation.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.CustomerResItem
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.login.ApiResponse
import retrofit2.Response

class CustomersRepo (private val token: String) {

    private val apiConsumer = AppModule().getRetrofitInstance(token)

     suspend fun createCustomer(customer: CustomerRequest): Response<ApiResponse<CustomerResponse>> {
        return apiConsumer.createCustomer(customer)

    }
    suspend fun getAllCustomers(): Response<ApiResponse<List<CustomerResItem>>> {
        return apiConsumer.getCustomers()
    }


}