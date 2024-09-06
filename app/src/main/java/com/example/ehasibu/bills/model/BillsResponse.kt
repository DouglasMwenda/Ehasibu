package com.example.ehasibu.bills.model

data class BillsResponse(
    val bills: List<Bill>,
    val message: String,
    val statusCode: Int
)