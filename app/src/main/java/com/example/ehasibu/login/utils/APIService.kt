package com.example.ehasibu.login.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIService {
    private const val BASE_URL = "http://192.168.88.206:9922/api/v1/"




      val instance: APIConsumer by lazy {
          val retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

          retrofit.create(APIConsumer::class.java)

      }
}