package com.example.ehasibu.budget.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.budget.data.AddBudgetResponse
import com.example.ehasibu.budget.data.BudgetRequest
import com.example.ehasibu.budget.data.Entity
import com.example.ehasibu.budget.data.UpdateBudgetRequest
import com.example.ehasibu.login.ApiResponse
import retrofit2.Response

class BudgetRepository (private val token: String) {
    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun getAllBudgets(): Response<List<Entity>> {
        return apiConsumer.fetchBudgets()
    }

    suspend fun addBudget( budget: BudgetRequest): Response<AddBudgetResponse> {
        return apiConsumer.addBudget(budget)
    }

    suspend fun updateBudget(budget: UpdateBudgetRequest) : Response<AddBudgetResponse> {
        return  apiConsumer.updateBudget(budget)
    }
}