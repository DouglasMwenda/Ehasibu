package com.example.ehasibu.vendors.moddel

data class VendorResponse(
    val entity: List<Entity>,
    val message: String,
    val statusCode: Int
)