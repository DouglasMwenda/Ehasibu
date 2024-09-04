package com.example.ehasibu.budget.viemodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.budget.data.Entity
import com.example.ehasibu.budget.repo.BudgetRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class BudgetViewModel (private val repo : BudgetRepository): ViewModel() {
    private val _budgets = MutableLiveData<List<Entity>?> (emptyList())
    val budgets : MutableLiveData<List<Entity>?> get() = _budgets

    init {
        getBudgets()
    }

    private fun getBudgets() {
        viewModelScope.launch {
            while(isActive)
                try {
                    val response = repo.getAllBudgets()
                    if(response.isSuccessful) {
                        response.body()?.let {
                            _budgets.value = it
                        }
                    }
                    delay(10000)
                }
                catch (t: Throwable) {

                }
        }
    }

}