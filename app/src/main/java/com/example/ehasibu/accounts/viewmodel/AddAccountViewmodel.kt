package com.example.ehasibu.accounts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.accounts.data.AccountRequest
import com.example.ehasibu.accounts.data.AccountsEditRequest
import com.example.ehasibu.accounts.repo.AccountRepo
import com.example.ehasibu.customerinformation.repo.CustomersRepo
import com.example.ehasibu.customerinformation.viewmodel.AddCustomerViewmodel
import kotlinx.coroutines.launch

private const val TAG = "addaccount"

class AddAccountViewmodel (private val repo: AccountRepo) : ViewModel() {

    private val _isAccountAdded= MutableLiveData<Boolean>()
    val isAccountAdded: LiveData<Boolean> = _isAccountAdded

    fun createCustomer(account : AccountRequest){
        viewModelScope.launch {
            try {
                val response= repo.createAccount(account)

                if (response.isSuccessful) {
                    _isAccountAdded.postValue(true)
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

    fun updateCustomer(accountRequest: AccountsEditRequest){
        viewModelScope.launch {
            try {
                val response= repo.updateAccount(accountRequest)

                if (response.isSuccessful) {
                    _isAccountAdded.postValue(true)
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