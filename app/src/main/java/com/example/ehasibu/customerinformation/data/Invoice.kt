package com.example.ehasibu.customerinformation.data

data class Invoice(
    val amount: Int,
    val amountForVat: Int,
    val amountPaid: Int,
    val business: Any,
    val customerName: String,
    val deleted: Boolean,
    val description: String,
    val dueDate: String,
    val invoiceDate: String,
    val invoiceId: String,
    val outputTax: Int,
    val price: Int,
    val product: List<Any>,
    val productName: String,
    val products: List<Any>,
    val quantity: Int,
    val sales: List<Any>,
    val status: String,
    val totalAmount: Int,
    val unit: String
)