package com.example.ehasibu.customerinformation.data

data class CustomerRequest(
    val address: String,
    val companyName: String,
    val customerFirstName: String,
    val customerLastName: String,
    val customerType: String,
    val emailAddress: String,
    val phoneNumber: String
)