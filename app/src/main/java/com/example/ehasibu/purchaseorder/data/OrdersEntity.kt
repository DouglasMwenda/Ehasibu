package com.example.ehasibu.purchaseorder.data

data class OrdersEntity(
    val bill: Any,
    val business: Any,
    val date: Any,
    val deliveryDate: String,
    val id: String,
    val products: List<Product>,
    val purchaseDate: String,
    val purchaseStatus: String,
    val user: Any,
    val vendor: Vendor
)