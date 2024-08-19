package com.example.ehasibu.utils.api

import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.CustomerResItem
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.customerinformation.data.UpdateCustomerRequest
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.data.AuthUserResponse
import com.example.ehasibu.login.data.OtpRequest
import com.example.ehasibu.login.data.OtpRequest2
import com.example.ehasibu.login.data.PassRequest
import com.example.ehasibu.login.data.PassResetRequest
import com.example.ehasibu.login.data.UserRequest
import com.example.ehasibu.login.forgot_password.ForgotPassResponse
import com.example.ehasibu.login.forgot_password.OtpValResponse
import com.example.ehasibu.login.reset_password.PasswordRequest
import com.example.ehasibu.product.data.DelResponse
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProdResponse
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.data.ProductResponse
import com.example.ehasibu.productsales.data.SalesEntity
import com.example.ehasibu.productsales.data.SalesPResponse
import com.example.ehasibu.purchaseorder.data.ApproveResponse
import com.example.ehasibu.purchaseorder.data.OrderEntity
import com.example.ehasibu.purchaseorder.data.OrderResponse
import com.example.ehasibu.purchaseorder.data.PoRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface APIConsumer {

    @POST("auth/login")
    fun login(@Body loginRequest: UserRequest): Call<ApiResponse<AuthUserResponse>>

    @POST("auth/resetPassword")
    fun passwordReset(@Body loginRequest: PassRequest): Call<ApiResponse<PasswordRequest>>

    @POST("auth/verifyOTP")
    fun otpVerify(@Body loginRequest: OtpRequest): Call<ApiResponse<AuthUserResponse>>

    @POST("auth/sendForgotPasswordOtp")
    fun passOtpSend(@Body loginRequest: PassResetRequest): Call<ApiResponse<ForgotPassResponse>>

    @POST("auth/validateForgotPasswordOTP")
    fun otpValidate(@Body loginRequest: OtpRequest2): Call<ApiResponse<OtpValResponse>>

    @POST("products/add")
    suspend fun addProduct(@Body product: ProductRequest): Response<ApiResponse<ProductResponse>>


    @GET("products/findAllProducts")
    suspend fun getAllProducts(): Response<ApiResponse<List<ProdResponse>>>

    @GET("products/Find by product Name")
    suspend fun fetchProduct(@Query("productName") productName: String): Response<ApiResponse<ProdResponse>>

    @DELETE("products/productId")
    suspend fun deleteProduct(@Query("productId") productId: String): Response<DelResponse>

    @PUT("products/updateProduct")
    suspend fun updateProduct(@Body product: EditRequest): Response<DelResponse>


    //productsales
    @GET("sales/findAllSales")
    suspend fun getSales(): Response<SalesPResponse<List<SalesEntity>>>


    //customers
    @POST("customers/customers")
    suspend fun createCustomer(@Body customer: CustomerRequest): Response<ApiResponse<CustomerResponse>>

    @GET("customers/customers")
    suspend fun getCustomers(): Response<ApiResponse<List<CustomerResItem>>>

    @PUT("customers/customers/{id}")
    suspend fun updateCustomer(@Body customer: UpdateCustomerRequest): Response<ApiResponse<CustomerResItem>>

    @DELETE("customers/{id}")
    suspend fun deleteCustomer(@Query("id") id: Int): Response<DelResponse>


    //Purchases
    @GET("purchases/getAllPurchases")
    suspend fun fetchOrders(): Response<OrderResponse<List<OrderEntity>>>

    @PUT("purchases/approvePurchase")
    suspend fun approveOrder(@Query("id") id: String): Response<ApproveResponse>

    @POST("purchases/addPurchase")
    suspend fun addOrder(@Body order: PoRequest): Response<OrderResponse<List<OrderEntity>>>
}


