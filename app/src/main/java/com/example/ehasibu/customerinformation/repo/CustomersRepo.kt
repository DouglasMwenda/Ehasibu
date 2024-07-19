package com.example.ehasibu.customerinformation.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.CustomerResponse
import retrofit2.Response

class CustomersRepo (private val token: String) {

    private val apiConsumer = AppModule().getRetrofitInstance(token)

     suspend fun createCustomer(customer: CustomerRequest): Response<CustomerResponse>{
        return apiConsumer.createCustomer(customer)

    }
}