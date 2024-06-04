package com.example.ehasibu.login.api

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
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.POST

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

}


