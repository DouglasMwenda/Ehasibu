package com.example.ehasibu.expenses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.expenses.model.ExpenseRequest
import com.example.ehasibu.expenses.repo.ExpenseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExpenseViewModel(private val repo: ExpenseRepo) : ViewModel() {
    private val _isExpenseAdded = MutableLiveData<Boolean>()
    val isExpenseAdded: LiveData<Boolean> get() = _isExpenseAdded
     fun addExpense(expense: ExpenseRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.addExpense(expense)
                if (response.isSuccessful) {
                    _isExpenseAdded.postValue(true)
                }
            } catch (e: Exception) {
            }
        }
    }

}

class AddExpProvider(val repo: ExpenseRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddExpenseViewModel(repo) as T

    }
}
