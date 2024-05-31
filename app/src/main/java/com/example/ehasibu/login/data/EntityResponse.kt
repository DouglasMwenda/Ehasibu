package com.example.ehasibu.login.data

data class EntityResponse<T>(
    val entity: T?,
    val message: String,
    val statusCode: Int
)