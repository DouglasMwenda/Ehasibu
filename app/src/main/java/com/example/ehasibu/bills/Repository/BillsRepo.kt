package com.example.ehasibu.bills.Repository

import com.example.ehasibu.AppModule
import com.example.ehasibu.bills.model.BillsResponse
import retrofit2.Response

class BillsRepo(private val token: String) {

    private val apiConsumer= AppModule().getRetrofitInstance(token)

    suspend fun getAllBills(): Response<BillsResponse>{
        return apiConsumer.fetchBills()
    }
}