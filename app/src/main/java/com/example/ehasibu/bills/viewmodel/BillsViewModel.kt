package com.example.ehasibu.bills.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.bills.Repository.BillsRepo
import com.example.ehasibu.bills.model.Bill
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class BillsViewModel(private val repo: BillsRepo) : ViewModel() {
    val bills = MutableLiveData<List<Bill>?>(emptyList())
    //val bill: LiveData<List<Bill>?> get() = _bills


    init {
        fetchBills()
    }

    private fun fetchBills() {
        viewModelScope.launch {
            while (isActive) {

                try {
                    val response = repo.getAllBills()
                    if (response.isSuccessful) {
                        response.body()?.entity?.let {
                            bills.value = it
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
}

class BillsProvider(val repo: BillsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BillsViewModel(repo) as T
    }

}