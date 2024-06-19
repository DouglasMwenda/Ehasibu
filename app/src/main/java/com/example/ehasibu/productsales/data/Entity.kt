package com.example.ehasibu.productsales.data

data class Entity(
    val amountForVat: Int,
    val customerId: Int,
    val customerName: String,
    val dueDate: Any,
    val invoiceId: String,
    val modeOfPayment: String,
    val saleDetails: List<SaleDetail>,
    val saleId: String,
    val salesDate: String,
    val status: String,
    val totalAmount: Int,
    val totalTax: Int
)