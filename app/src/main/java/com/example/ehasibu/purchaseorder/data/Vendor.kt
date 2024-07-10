package com.example.ehasibu.purchaseorder.data

data class Vendor(
    val address: String,
    val bills: List<Any>,
    val createdAt: String,
    val deleted: Boolean,
    val displayName: String,
    val email: String,
    val otherDetails: String,
    val phone: String,
    val vendorId: String,
    val vendorName: String,
    val vendorPin: String,
    val vendorType: String
)