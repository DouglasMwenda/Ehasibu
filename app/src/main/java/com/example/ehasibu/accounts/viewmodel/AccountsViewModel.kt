package com.example.ehasibu.accounts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.accounts.data.AccountsEntity
import com.example.ehasibu.accounts.repo.AccountRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AccountsViewModel (private val repo : AccountRepo) : ViewModel() {
     private val accounts = MutableLiveData<List<AccountsEntity>?>(emptyList())
    val account : MutableLiveData<List<AccountsEntity>?> get() = accounts

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
                }
                catch (t: Throwable) {

                }
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