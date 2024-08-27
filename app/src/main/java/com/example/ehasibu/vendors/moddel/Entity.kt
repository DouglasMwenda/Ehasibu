package com.example.ehasibu.vendors.moddel

data class Entity(
    val address: String,
    val bills: List<Any>,
    val createdAt: String,
    val deleted: Boolean,
    val displayName: String,
    val email: String,
    val otherDetails: String,
    val phone: String,
    val vendorAccountNumber: Any,
    val vendorId: String,
    val vendorName: String,
    val vendorPin: String,
    val vendorType: String
)