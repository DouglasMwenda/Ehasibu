package com.example.ehasibu.vendors.moddel

import com.example.ehasibu.AppModule
import com.example.ehasibu.product.data.DelResponse
import retrofit2.Response

class VendorRepo(private val token: String) {
    private val apiConsumer = AppModule().getRetrofitInstance(token)

    suspend fun getVendors(): Response<VendorResponse> {
        return apiConsumer.fetchVendors()

    }
    suspend fun editVendor(vendor: EditVRequest): Response<EditResponse>{
        return apiConsumer.updateVendor(vendor)
    }

    suspend fun deleteVendor(vendorId: String): Response<DelResponse> {
        return apiConsumer.deleteVendor(vendorId)
    }
    suspend fun addVendor(vendor: AddRequest): Response<AddVendorResponse> {
        return apiConsumer.addVendor(vendor)
    }


}