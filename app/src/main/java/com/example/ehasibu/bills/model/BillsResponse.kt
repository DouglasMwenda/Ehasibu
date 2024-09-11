package com.example.ehasibu.bills.model

data class BillsResponse<T>(
    val bills: T,
    val message: String,
    val statusCode: Int
)