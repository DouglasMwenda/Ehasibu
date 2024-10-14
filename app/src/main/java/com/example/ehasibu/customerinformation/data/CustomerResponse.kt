package com.example.ehasibu.customerinformation.data

import java.io.Serializable

data class CustomerResponse(
    val address: String,
    val companyName: String,
    val createdAt: String,
    val customerFirstName: String,
    val customerId: Long,
    val customerLastName: String,
    val customerType: String,
    val deleted: Boolean,
    val emailAddress: String,
    val entryDate: String,
    val invoices: Any,
    val phoneNumber: String
) : Serializable

