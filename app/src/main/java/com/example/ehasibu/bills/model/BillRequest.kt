package com.example.ehasibu.bills.model

data class BillRequest(
    val amountForVAT: Int,
    val buyingPrice: Int,
    val paymentDate: String,
    val poNumber: String,
    val quantity: Int,
    val vendor: String,
    val vendorId: String
)