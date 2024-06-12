package com.example.ehasibu.product.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.product.data.ProdResponse
import retrofit2.Response

class ProductRepository(private val token: String) {

    private val APIConsumer = AppModule().getRetrofitInstance(token)

    suspend fun getAllProducts(): Response<ApiResponse<List<ProdResponse>>> {
        return APIConsumer.getAllProducts()
    }
}
