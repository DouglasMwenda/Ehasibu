package com.example.ehasibu.productsales.data

data class SalesPResponse<T>(
    val entity: T,
    val message: String,
    val statusCode: Int
)