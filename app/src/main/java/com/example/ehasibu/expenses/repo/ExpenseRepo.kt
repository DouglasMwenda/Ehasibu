package com.example.ehasibu.expenses.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.expenses.model.ExpAddResponse
import com.example.ehasibu.expenses.model.ExpenseRequest
import com.example.ehasibu.expenses.model.ExpensesResponse
import retrofit2.Response

class ExpenseRepo(private val token: String) {
    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun fetchExpenses(): Response<ExpensesResponse> {
        return apiConsumer.fetchExpenses()
    }
    suspend fun addExpense(expense: ExpenseRequest):Response<ExpAddResponse>{
        return apiConsumer.addExpense(expense)
    }
}