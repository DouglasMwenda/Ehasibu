package com.example.ehasibu.product.data

data class ProductResponse<T>(
    val entity: T,
    val message: String,
    val statusCode: Int
)