package com.example.ehasibu.product.data

data class Entity(
    val amount: Double,
    val availableForSale: Boolean,
    val buyingPrice: Double,
    val category: String,
    val createdBy: Int,
    val createdDate: String,
    val deleted: Boolean,
    val description: String,
    val invoice: Any,
    val lastModifiedBy: Int,
    val lastModifiedDate: Any,
    val productId: String,
    val productName: String,
    val quantity: Int,
    val quantityPurchased: Int,
    val quantitySold: Int,
    val quotation: Any,
    val sellingPrice: Double,
    val totalDaysInInventory: Int,
    val unit: String,
    val user: Any
)