package com.example.ehasibu.customerinformation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.data.UpdateCustomerRequest
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import kotlinx.coroutines.launch

private const val TAG ="addcustomer"

class AddCustomerViewmodel(private val repo: CustomersRepo):  ViewModel() {

    private val _isCustomerAdded= MutableLiveData<Boolean>()
    val isCustomerAdded: LiveData<Boolean>  = _isCustomerAdded

    fun createCustomer(customer: CustomerRequest){
        viewModelScope.launch {
            try {
                val response= repo.createCustomer(customer)

                if (response.isSuccessful) {
                    _isCustomerAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (_: Throwable) {
            }
        }
    }

    fun updateCustomer(customerRequest: UpdateCustomerRequest){
        viewModelScope.launch {
            try {
                val response= repo.updateCustomer(customerRequest)

                if (response.isSuccessful) {
                    _isCustomerAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (_: Throwable) {
            }
        }
    }



    class AddCustomerProvider( val repo: CustomersRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddCustomerViewmodel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddCustomerViewmodel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}