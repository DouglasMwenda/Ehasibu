package com.example.ehasibu.login.data

import java.io.Serializable

data class UserRequest(
    val email: String,
    val password: String,

): Serializable
data class PassRequest(
    val email: String,
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String,

    )
data class OtpRequest(
    val otp: String,
    val email: String,
)