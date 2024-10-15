package com.example.ehasibu.expenses.model

data class Entity(
    val accounts: List<Any>,
    val amountSpent: Double,
    val budgetType: String,
    val business: Business,
    val category: String,
    val deleted: Boolean,
    val expenseDate: String,
    val expenseType: String,
    val id: Long,
    val modeOfPayment: String,
    val status: String
)