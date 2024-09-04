package com.example.ehasibu.budget.data

data class BudgetRequest(
    val amountBudgeted: Int,
    val budgetType: String,
    val description: String,
    val period: String
)