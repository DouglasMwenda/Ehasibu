package com.example.ehasibu.customerinformation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.login.ApiResponse
import kotlinx.coroutines.launch

class CustomersViewModel (private val repo: CustomersRepo): ViewModel() {
    private val _customers= MutableLiveData<ApiResponse<CustomerResponse>>()
    val customer: LiveData<ApiResponse<CustomerResponse>> get() = _customers


    fun getCustomers(customer: CustomerRequest){
        viewModelScope.launch {
            try {
                val response= repo.createCustomer(customer)

            if (response.isSuccessful) {
                (response.body()?. let {
                    _customers.value = it
                }) ?: run {
                    Log.d(TAG, "message: ${response.message()}")
                }
            } else {
                Log.d(TAG, "message: ${response.message()}")
            }
        } catch (t: Throwable) {
            Log.e(TAG, "Exception occurred: ${t.message}", t)
        }
        }
    }

}