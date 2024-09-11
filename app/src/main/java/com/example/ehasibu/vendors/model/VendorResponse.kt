package com.example.ehasibu.vendors.model

data class VendorResponse(
    val entity: List<Entity>,
    val message: String,
    val statusCode: Int
)