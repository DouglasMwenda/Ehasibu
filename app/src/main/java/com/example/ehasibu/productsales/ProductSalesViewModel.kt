package com.example.ehasibu.productsales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductSalesViewModel (salesRepository: SalesRepository) : ViewModel() {
    private val _customers = MutableLiveData<List<String>>()
    val customers: LiveData<List<String>> = _customers
    private val _summaryTotal = MutableLiveData<String>()
    val summaryTotal: LiveData<String> = _summaryTotal
    private val _summaryTax = MutableLiveData<String>()
    val summaryTax: LiveData<String> = _summaryTax
    private val _summaryNetTotal= MutableLiveData<String>()
    val summaryNetTotal: LiveData<String> =_summaryNetTotal
    private val _amountPaid= MutableLiveData<String>()
    val amountPaid: LiveData<String> =_amountPaid
    val salesData: LiveData<List<SalesItem>> = salesRepository.getSalesData()




    fun loadCustomers() {

        val customerList = listOf("Customer 1", "Customer 2", "Customer 3")
        _customers.value = customerList
    }
}

private fun Any.getSalesData(): LiveData<List<SalesItem>> {
return getSalesData()
}
