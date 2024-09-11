package com.example.ehasibu.budget.viemodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.budget.data.BudgetRequest
import com.example.ehasibu.budget.data.UpdateBudgetRequest
import com.example.ehasibu.budget.repo.BudgetRepository
import kotlinx.coroutines.launch

private const val TAG = "add budget"

class AddBudgetViewModel(private val repo: BudgetRepository) : ViewModel() {
    private val _isBudgetAdded = MutableLiveData<Boolean>()
    val isBudgetAdded: LiveData<Boolean> = _isBudgetAdded

    fun addBudget(budget: BudgetRequest) {
        viewModelScope.launch {
            try {
                val response = repo.addBudget(budget)
                if (response.isSuccessful) {
                    _isBudgetAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (t: Throwable) {

            }
        }
    }

    fun editBudget(budgetRequest: UpdateBudgetRequest) {
        viewModelScope.launch {
            try {
                val response = repo.updateBudget(budgetRequest)
                if (response.isSuccessful) {
                    _isBudgetAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (t: Throwable) {

            }
        }
    }
}

class AddBudgetProvider(val repo: BudgetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBudgetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddBudgetViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

