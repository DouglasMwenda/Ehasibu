package com.example.ehasibu.bills.Repository

import com.example.ehasibu.AppModule
import com.example.ehasibu.bills.model.AddBillResponse
import com.example.ehasibu.bills.model.Bill
import com.example.ehasibu.bills.model.BillRequest
import com.example.ehasibu.bills.model.BillsResponse
import retrofit2.Response

class BillsRepo(private val token: String) {

    private val apiConsumer= AppModule().getRetrofitInstance(token)

    suspend fun getAllBills(): Response<BillsResponse<List<Bill>>>{

        return apiConsumer.fetchBills()

    }

    suspend fun addBill(bill: BillRequest): Response<AddBillResponse>{
        return apiConsumer.addBill(bill)

    }
}