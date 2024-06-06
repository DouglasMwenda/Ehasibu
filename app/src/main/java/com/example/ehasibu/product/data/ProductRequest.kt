package com.example.ehasibu.product.data

data class ProductRequest(
    val category: String,
    val description: String,
    val productId: Int,
    val productName: String,
    val unit: String
)