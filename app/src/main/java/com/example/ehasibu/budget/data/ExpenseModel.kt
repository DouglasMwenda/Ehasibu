package com.example.ehasibu.budget.data

data class ExpenseModel(
    val accounts: List<Any>,
    val amountSpent: Int,
    val budgetType: String,
    val business: Business,
    val category: String,
    val deleted: Boolean,
    val expenseDate: String,
    val expenseType: String,
    val id: Int,
    val modeOfPayment: Any,
    val status: String
)