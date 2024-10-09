package com.example.ehasibu.budget.data

import java.io.Serializable

data class Entity(
    val amountBudgeted: Int,
    val amountSpent: Int,
    val budgetBalance: Int,
    val budgetId: Int,
    val budgetType: String,
    val date: String,
    val deleted: Boolean,
    val description: String,
    val expenseModel: Any,
    val period: String
) : Serializable