package com.example.ehasibu.login

data class ApiResponse<T>(
    val entity: T,
    val message: String,
    val statusCode: Int
)