package com.example.ehasibu.expenses.model

data class Business(
    val alternativeContact: String,
    val businessAddress: String,
    val businessEmail: String,
    val businessName: String,
    val businessRegistrationNumber: String,
    val businessType: String,
    val contact: String,
    val createdBy: Any,
    val createdDate: String,
    val id: Int,
    val lastModifiedBy: Int,
    val lastModifiedDate: String,
    val registrationStatus: String,
    val vatRegistrationStatus: String
)