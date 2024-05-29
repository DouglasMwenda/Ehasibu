package com.example.ehasibu.login.api

import com.example.ehasibu.login.data.EntityResponse
import com.example.ehasibu.login.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {

    @POST("auth/login")
    suspend fun login (@Body Body: User): Response<EntityResponse>

}