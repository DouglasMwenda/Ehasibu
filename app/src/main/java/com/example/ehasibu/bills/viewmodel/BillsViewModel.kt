package com.example.ehasibu.bills.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.bills.Repository.BillsRepo
import com.example.ehasibu.bills.model.BillsResponse
import kotlinx.coroutines.launch

class BillsViewModel(private val repo: BillsRepo) : ViewModel() {
    val bill = MutableLiveData<BillsResponse>()

    init {
        fetchBills()
    }

    private fun fetchBills() {
        viewModelScope.launch {
            try {
                val response = repo.getAllBills()
                if (response.isSuccessful) {
                    response.body()?.bills?.let {
                        bill.value
                    }
                } else {
                    Log.e(TAG, "Error fetching bills: ${response.code()}")
                }

            } catch (t: Throwable) {
                Log.e(TAG, "Exception occurred: ${t.message}", t)

            }
        }

    }
}

class BillsProvider(val repo: BillsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BillsViewModel(repo) as T
    }

}