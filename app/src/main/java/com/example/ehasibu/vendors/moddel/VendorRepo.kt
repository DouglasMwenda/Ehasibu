package com.example.ehasibu.vendors.moddel

import com.example.ehasibu.AppModule
import retrofit2.Response

class VendorRepo(private val token: String) {
    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun getVendors(): Response<VendorResponse> {
        return apiConsumer.fetchVendors()

    }

}