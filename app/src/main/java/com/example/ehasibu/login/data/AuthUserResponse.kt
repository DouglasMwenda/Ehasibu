package com.example.ehasibu.login.data

data class AuthUserResponse(
    val access_token: String,
    val businessResponse: BusinessResponse,
    val email: String,
    val firstLogin: Boolean,
    val id: Int,
    val primaryRole: String,
    val roles: List<Role>
)