package com.example.ehasibu.customerinformation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.customerinformation.data.CustomerResItem
import com.example.ehasibu.customerinformation.data.CustomerResponse
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CustomersViewModel(private val repo: CustomersRepo) : ViewModel() {
    private val _customers = MutableLiveData<List<CustomerResponse>?>(emptyList())
    val customer: MutableLiveData<List<CustomerResponse>?> get() = _customers

    init {
        getCustomers()
    }


    private fun getCustomers() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getAllCustomers()
                    if (response.isSuccessful) {
                        response.body()?.entity?.let {
                            _customers.value = it
                        }
                    }
                    delay(10000)
                } catch (t: Throwable) {

                }
            }
        }
    }

    class CustomerProvider(private val repo: CustomersRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CustomersViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CustomersViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}