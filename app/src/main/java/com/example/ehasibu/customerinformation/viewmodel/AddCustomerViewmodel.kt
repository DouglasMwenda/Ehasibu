package com.example.ehasibu.customerinformation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.customerinformation.data.CustomerRequest
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import kotlinx.coroutines.launch

class AddCustomerViewmodel(private val repo: CustomersRepo):  ViewModel() {

    private val _customers= MutableLiveData<Boolean>()
    val customer: LiveData<Boolean> get() = _customers

    fun createCustomer(customer: CustomerRequest){
        viewModelScope.launch {
            try {
                val response= repo.createCustomer(customer)

                if (response.isSuccessful) {
                    _customers.postValue(true)

                    }
            } catch (t: Throwable) {
            }
        }
    }

}