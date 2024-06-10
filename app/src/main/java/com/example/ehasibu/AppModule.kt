package com.example.ehasibu

import com.example.ehasibu.utils.BASE_URL
import com.example.ehasibu.utils.api.APIConsumer
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModuleInterface {
    fun getRetrofitInstance(token: String): APIConsumer
}
class AppModule: AppModuleInterface {
    override fun getRetrofitInstance(token: String): APIConsumer {

        val interceptor = TokenInterceptor(token)
         val client: OkHttpClient = OkHttpClient.Builder()
             .addInterceptor(interceptor)
            .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val instance: APIConsumer by lazy {
            retrofit.create(APIConsumer::class.java)
        }
        return instance
    }
}

class TokenInterceptor(private val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(req)
    }

}