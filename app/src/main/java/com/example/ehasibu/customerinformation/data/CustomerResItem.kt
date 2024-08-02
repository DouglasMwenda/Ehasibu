package com.example.ehasibu.customerinformation.data

data class CustomerResItem(
    val address: String,
    val companyName: String,
    val createdAt: String,
    val customerFirstName: String,
    val customerId: Int,
    val customerLastName: String,
    val customerType: String,
    val deleted: Boolean,
    val emailAddress: String,
    val entryDate: String,
    val invoices: List<Invoice>,
    val phoneNumber: String
)