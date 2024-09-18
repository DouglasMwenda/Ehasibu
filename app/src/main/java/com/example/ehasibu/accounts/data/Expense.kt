package com.example.ehasibu.accounts.data

data class Expense(
    val accounts: List<String>,
    val amountSpent: Int,
    val budgetType: String,
    val business: Business,
    val category: String,
    val deleted: Boolean,
    val expenseDate: String,
    val expenseType: String,
    val id: Int,
    val modeOfPayment: String,
    val status: String
)