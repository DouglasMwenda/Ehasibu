package com.example.ehasibu.bills.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.bills.Repository.BillsRepo
import com.example.ehasibu.bills.model.BillRequest
import kotlinx.coroutines.launch

class NewBillViewModel(private val repository: BillsRepo) : ViewModel() {
    private val _isBillAdded = MutableLiveData<Boolean>()
    val isBillAdded: LiveData<Boolean> = _isBillAdded

    fun addBill(bill: BillRequest) {
        viewModelScope.launch {
            try {
                val response = repository.addBill(bill)
                if (response.isSuccessful) {
                    _isBillAdded.value = true
                } else {
                    _isBillAdded.value = false
                }
            } catch (e: Exception) {
                _isBillAdded.value = false
            }

        }
    }
}
class NewBillProvider(private val repository: BillsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewBillViewModel(repository) as T
    }

}