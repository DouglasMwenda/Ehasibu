package com.example.ehasibu.login.utils

import com.example.ehasibu.login.data.EmailResponse
import com.example.ehasibu.login.data.ValidateEmail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {

    @POST("users/login")
    fun validateUser(@Body Body: ValidateEmail): Response<EmailResponse>

}