package com.example.ehasibu.login.data

import androidx.compose.ui.semantics.Role

data class AuthUserResponse(
    val access_token: String,
    val businessResponse: BusinessResponse,
    val email: String,
    val firstLogin: Boolean,
    val id: Int,
    val primaryRole: String,
    val roles: List<Role>
)