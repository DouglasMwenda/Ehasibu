package com.example.ehasibu.expenses.model

data class ExpenseRequest(
    val amountSpent: Double,
    val budgetType: String,
    val category: String,
    val expenseType: String,
    val modeOfPayment: String
)