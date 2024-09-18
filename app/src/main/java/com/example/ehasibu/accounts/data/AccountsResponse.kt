package com.example.ehasibu.accounts.data

data class AccountsResponse(
    val entity: List<AccountsEntity>,
    val message: String,
    val statusCode: Int
)