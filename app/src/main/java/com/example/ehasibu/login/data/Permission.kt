package com.example.ehasibu.login.data

data class Permission(
    val action: String,
    val description: String,
    val id: Int,
    val name: String,
    val resource: String
)