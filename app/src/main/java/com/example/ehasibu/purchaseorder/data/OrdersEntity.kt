package com.example.ehasibu.purchaseorder.data

import java.io.Serializable

data class OrderEntity(
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
): Serializable

data class OrderResponse<T>(
    val entity: T,
    val message: String,
    val statusCode: Int
)