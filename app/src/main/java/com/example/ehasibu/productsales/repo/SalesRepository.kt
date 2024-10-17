package com.example.ehasibu.productsales.repo

import com.example.ehasibu.AppModule
import com.example.ehasibu.productsales.data.AddSaleRequest
import com.example.ehasibu.productsales.data.SalesEntity
import com.example.ehasibu.productsales.data.SalesPResponse
import retrofit2.Response

class SalesRepository (private val token: String) {
    private val apiconsumer= AppModule().getRetrofitInstance(token)

    suspend fun getAllSales(): Response<SalesPResponse<List<SalesEntity>>> {
        return apiconsumer.getSales()
    }

    suspend fun createSale(sale : AddSaleRequest): Response<SalesPResponse<SalesEntity>> {
        return apiconsumer.addSale(sale)
    }


}
