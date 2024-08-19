package com.example.ehasibu.purchaseorder.data

data class EntityX(
    val bill: Any,
    val deliveryDate: String,
    val id: Any,
    val products: List<Product>,
    val purchaseDate: String,
    val purchaseDetails: Any,
    val purchaseStatus: String,
    val vendorName: String
)