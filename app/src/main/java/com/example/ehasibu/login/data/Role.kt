package com.example.ehasibu.login.data

data class Role(
    val id: Int,
    val name: String,
    val permissions: List<Permission>
)