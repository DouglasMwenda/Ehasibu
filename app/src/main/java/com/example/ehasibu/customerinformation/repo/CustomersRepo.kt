package com.example.ehasibu.customerinformation.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.customerinformation.data.UpdateCustomerRequest
import com.example.ehasibu.login.ApiResponse
import retrofit2.Response

class CustomersRepo (private val token: String) {

    private val apiConsumer = AppModule().getRetrofitInstance(token)

     suspend fun createCustomer(customer: CustomerRequest): Response<ApiResponse<CustomerResponse>> {
        return apiConsumer.createCustomer(customer)

    }
    suspend fun getAllCustomers(): Response<List<CustomerResponse>> {
        return apiConsumer.getCustomers()
    }

    suspend fun updateCustomer( customer: UpdateCustomerRequest): Response<ApiResponse<CustomerResponse>> {
        return apiConsumer.updateCustomer(customer)
    }

    suspend fun deleteCustomer( customerId: Int) : Response<ApiResponse<CustomerResponse>> {
        return apiConsumer.deleteCustomer(customerId)

    }


}