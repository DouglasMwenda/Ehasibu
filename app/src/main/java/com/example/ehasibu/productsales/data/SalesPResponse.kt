package com.example.ehasibu.productsales.data

data class SalesPResponse(
    val entity: List<Entity>,
    val message: String,
    val statusCode: Int
)