package com.example.ehasibu.budget.data

data class ExpenseModelX(
    val accounts: List<Account>,
    val amountSpent: Int,
    val budgetType: String,
    val business: BusinessXX,
    val category: String,
    val deleted: Boolean,
    val expenseDate: String,
    val expenseType: String,
    val id: Int,
    val modeOfPayment: String,
    val status: String
)