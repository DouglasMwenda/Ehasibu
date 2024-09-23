package com.example.ehasibu.expenses.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.expenses.model.ExpenseResponse
import retrofit2.Response

class ExpenseRepo(private val token: String) {
    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun fetchExpenses(): Response<ExpenseResponse> {
        return apiConsumer.fetchExpenses()
    }
}