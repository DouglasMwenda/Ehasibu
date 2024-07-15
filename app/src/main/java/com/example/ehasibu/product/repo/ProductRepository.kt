package com.example.ehasibu.product.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.product.data.DelResponse
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.data.ProductResponse
import retrofit2.Response

class ProductRepository(private val token: String) {

    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun getAllProducts(): Response<ApiResponse<List<ProdResponse>>> {
        return apiConsumer.getAllProducts()
    }

    suspend fun addProduct(product: ProductRequest): Response<ApiResponse<ProductResponse>> {
        return apiConsumer.addProduct(product)

    }

    suspend fun fetchProduct(productName: String): Response<ApiResponse<ProdResponse>> {
        return apiConsumer.fetchProduct(productName)
    }

    suspend fun deleteProduct(productId: String): Response<DelResponse> {
        return apiConsumer.deleteProduct(productId)
    }

    suspend fun updateProduct(product: EditRequest): Response<DelResponse> {
        return apiConsumer.updateProduct(product)
    }
}
