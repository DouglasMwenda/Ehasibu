package com.example.ehasibu.bills.model

data class Bill(
    val address: Any,
    val amountForVAT: Int,
    val amountPayable: Int,
    val billedAmount: Int,
    val createdAt: String,
    val deleted: Boolean,
    val getAmountPayable: Any,
    val id: Int,
    val inputTax: Int,
    val invoicePath: Any,
    val paymentDate: String,
    val paymentStatus: String,
    val poNumber: String,
    val status: String,
    val vendor: Vendor,
    val withholdingTaxPayable: Int
)