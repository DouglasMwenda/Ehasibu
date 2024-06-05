package com.example.ehasibu.productsales

data class SalesItem (
    val no: String,
    val customerName: String,
    val date: String,
    val netAmount: Double,
    val taxAmount: Double,
    val totalAmount: Double,
    val modeOfPayment: String,
)

