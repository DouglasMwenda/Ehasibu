package com.example.ehasibu.accounts.data

data class AccountsEntity(
    val accountBalance: Int,
    val accountCode: Int,
    val accountName: String,
    val accountType: String,
    val business: Business,
    val createdBy: String,
    val createdOn: String,
    val deleted: Boolean,
    val description: String,
    val expenses: List<Expense>
)