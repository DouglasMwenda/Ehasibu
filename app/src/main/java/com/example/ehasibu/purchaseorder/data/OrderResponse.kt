package com.example.ehasibu.purchaseorder.data

data class OrderResponse<T>(
    val entity: List<T>,
    val message: String,
    val statusCode: Int
)