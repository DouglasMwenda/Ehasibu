package com.example.ehasibu.bills.model

data class AddBillResponse(
    val entity: Entity,
    val message: String,
    val statusCode: Int
)