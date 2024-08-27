package com.example.ehasibu.vendors.moddel

import com.example.ehasibu.purchaseorder.data.Vendor

data class VendorResponse(
    val entity: List<Vendor>,
    val message: String,
    val statusCode: Int
)