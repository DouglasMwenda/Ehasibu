package com.example.ehasibu.purchaseorder.data

data class PoRequest(
    val deliveryDate: String,
    val products: List<ProductX>,
    val vendorId: String,
)