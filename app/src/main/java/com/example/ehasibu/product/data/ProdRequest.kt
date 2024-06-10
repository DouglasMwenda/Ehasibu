package com.example.ehasibu.product.data

data class ProdRequest(
    val entity: List<Entity>,
    val message: String,
    val statusCode: Int
)