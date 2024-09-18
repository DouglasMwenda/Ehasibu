package com.example.ehasibu.bills.model

data class BillsResponse<T>(
    val entity: T,
    val message: String,
    val statusCode: Int
)