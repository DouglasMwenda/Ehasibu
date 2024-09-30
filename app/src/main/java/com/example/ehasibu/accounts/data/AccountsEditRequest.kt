package com.example.ehasibu.accounts.data

data class AccountsEditRequest(
    val accountBalance: Int,
    val accountCode: Int,
    val accountName: String,
    val accountType: String,
    val balance: Int,
    val business: BusinessX,
    val createdBy: String,
    val createdOn: String,
    val creditBalance: Int,
    val debitBalance: Int,
    val deleted: Boolean,
    val description: String,
    val expenses: List<ExpenseX>
)