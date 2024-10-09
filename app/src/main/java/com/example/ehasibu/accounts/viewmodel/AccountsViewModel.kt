package com.example.ehasibu.accounts.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.accounts.data.AccountsEntity
import com.example.ehasibu.accounts.repo.AccountRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AccountsViewModel(private val repo: AccountRepo) : ViewModel() {
    private val accounts = MutableLiveData<List<AccountsEntity>?>(emptyList())
    val account: MutableLiveData<List<AccountsEntity>?> get() = accounts


    init {
        fetchAccounts()
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getAllAccounts()
                    if (response.isSuccessful) {
                        response.body()?.entity?.let {
                            accounts.value = it
                        }

                    }

                    delay(10000)
                } catch (t: Throwable) {

                }
            }
        }
    }

    fun deleteProduct(accountCode: Int) {
        viewModelScope.launch {
            try {
                val response = repo.deleteAccount(accountCode)
                if (response.isSuccessful) {
                    val updatedAccounts = accounts.value?.filterNot { it.accountCode == accountCode }
                    accounts.value = updatedAccounts
                    Log.d(ContentValues.TAG, "message: ${response.message()}")
                } else {
                    Log.d(ContentValues.TAG, "message: ${response.message()}")
                }
            } catch (t: Throwable) {
                Log.e(ContentValues.TAG, "Exception occurred: ${t.message}", t)
            }
        }
    }

}


class AccountsProvider(private val repo: AccountRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}