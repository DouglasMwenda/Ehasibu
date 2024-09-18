package com.example.ehasibu.expenses.model

data class ExpenseResponse(
    val entity: List<Entity>,
    val message: String,
    val statusCode: Int
)