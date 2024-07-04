package com.example.ehasibu.product.data

data class EditRequest(
    val productId: String,
    val productName: String,
    val description: String,
    val category: String,
    val unit: String
)
