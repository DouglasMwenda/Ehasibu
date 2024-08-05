package com.example.ehasibu.purchaseorder.data

data class Product(
    val amount: Int,
    val availableForSale: Boolean,
    val averageSalesVolume: Any,
    val buyingPrice: Int,
    val category: String,
    val createdBy: Int,
    val createdDate: String,
    val deleted: Boolean,
    val description: String,
    val invoice: Any,
    val lastModifiedBy: Int,
    val lastModifiedDate: String,
    val productId: String,
    val productName: String,
    val quantity: Int,
    val quantityPurchased: Int,
    val quantitySold: Int,
    val quotation: Any,
    val sellingPrice: Int,
    val totalDaysInInventory: Int,
    val unit: String,
    val user: Any
)
