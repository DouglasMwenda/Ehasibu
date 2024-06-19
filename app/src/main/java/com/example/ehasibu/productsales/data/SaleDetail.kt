package com.example.ehasibu.productsales.data

data class SaleDetail(
    val amount: Int,
    val category: String,
    val description: String,
    val id: Int,
    val invoiceId: Any,
    val price: Int,
    val productName: String,
    val quantitySold: Int,
    val unit: String
)