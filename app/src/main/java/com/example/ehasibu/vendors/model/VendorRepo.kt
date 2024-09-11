package com.example.ehasibu.vendors.model

import com.example.ehasibu.AppModule
import retrofit2.Response

class VendorRepo(private val token: String) {
    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun getVendors(): Response<VendorResponse> {
        return apiConsumer.fetchVendors()

    }
    suspend fun editVendor(vendor: EditVRequest): Response<EditResponse>{
        return apiConsumer.updateVendor(vendor)
    }

    suspend fun deleteVendor(vendorId: String): Response<DelVResponse> {
        return apiConsumer.deleteVendor(vendorId)    }

    suspend fun addVendor(vendor: AddRequest): Response<AddVendorResponse> {
        return apiConsumer.addVendor(vendor)
    }


}