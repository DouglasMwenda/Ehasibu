package com.example.ehasibu.login.api

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {




    private const val BASE_URL = "http://192.168.88.206:9922/api/v1/"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS,ConnectionSpec.CLEARTEXT))
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

      val instance: APIConsumer by lazy {


          retrofit.create(APIConsumer::class.java)

      }
}