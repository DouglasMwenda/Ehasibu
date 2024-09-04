package com.example.ehasibu.budget.data

data class Account(
    val accountBalance: Int,
    val accountCode: Int,
    val accountName: String,
    val accountType: String,
    val balance: Int,
    val business: BusinessXX,
    val createdBy: String,
    val createdOn: String,
    val creditBalance: Int,
    val debitBalance: Int,
    val deleted: Boolean,
    val description: String,
    val expenses: List<String>
)