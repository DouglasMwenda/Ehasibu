package com.example.ehasibu.expenses.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.expenses.model.Entity
import com.example.ehasibu.expenses.repo.ExpenseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel (private val repo: ExpenseRepo): ViewModel() {
     val expenses = MutableLiveData<List<Entity>?>()

    init {
        getExpenses()
    }

     private fun getExpenses() {

         viewModelScope.launch(Dispatchers.IO) {
             try {
                 val response = repo.fetchExpenses()
                 if (response.isSuccessful) {
                     response.body()?.entity?.let {
                         expenses.value = it
                     }
                     Log.d("", "Expenses fetched successfully: ${response.body()?.entity}")
                 } else {
                     Log.e(
                         "ExpensesViewModel",
                         "Error fetching expenses: ${response.errorBody()?.string()}"
                     )
                 }
             } catch (e: Exception) {
                 Log.e("ExpensesViewModel", "Error fetching expenses", e)
             }
         }


    }

   /* fun deleteExpense(id: Int) {
        viewModelScope.launch {
            try {
                val response = repo.deleteExpense(id)
                if (response.isSuccessful) {
                    Log.d("ExpensesViewModel", "deleted successfully")

                }
            }
            catch (e: Exception) {
                Log.e("ExpensesViewModel", "Error deleting expense", e)
            }
        }

    }*/
}
class ExpenseProvider (private val repo :ExpenseRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpensesViewModel(repo) as T
    }

}