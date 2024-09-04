package com.example.ehasibu.budget.data

data class updateBudgetRequest(
    val amountBudgeted: Int,
    val amountSpent: Int,
    val budgetBalance: Int,
    val budgetId: Int,
    val budgetType: String,
    val date: String,
    val deleted: Boolean,
    val description: String,
    val expenseModel: List<ExpenseModelX>,
    val period: String
)