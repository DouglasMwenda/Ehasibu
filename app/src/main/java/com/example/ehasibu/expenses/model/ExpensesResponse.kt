package com.example.ehasibu.expenses.model

data class ExpensesResponse(
    val entity: List<Entity>,
    val message: String,
    val statusCode: Int
)