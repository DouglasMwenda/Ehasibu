package com.example.ehasibu.productsales.data

data class AddSaleRequest(
    val amountForVat: Int,
    val amountPaid: Int,
    val customerId: Int,
    val customerName: String,
    val dueDate: String,
    val modeofpayment: String,
    val products: List<Product>,
    val salesDate: String,
    val status: String,
    val totalAmount: Int,
    val totalTax: Int
)