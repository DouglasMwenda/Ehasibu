package com.example.ehasibu.login.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://192.168.89.212:9922/api/v1/"
object APIService {

      val instance: APIConsumer by lazy {
          val retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

          retrofit.create(APIConsumer::class.java)

      }
}