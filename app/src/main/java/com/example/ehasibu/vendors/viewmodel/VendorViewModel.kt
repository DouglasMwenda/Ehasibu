package com.example.ehasibu.vendors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.purchaseorder.data.Vendor
import com.example.ehasibu.vendors.moddel.VendorRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class VendorViewModel(private val repo: VendorRepo) : ViewModel() {
    private val _vendors = MutableLiveData<List<Vendor>>()
    val vendors: LiveData<List<Vendor>> get() = _vendors

    fun updateVendors (vendor: List<Vendor>) {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val response = repo.getVendors()
                    if (response.isSuccessful) {
                        response.body()?.let { vendorResponse ->
                            _vendors.value = vendorResponse.entity
                        }
                    } else {
                        println("Error: ${response.errorBody()}")
                    }
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }

            delay(1000)

        }

    }

}
 class VendorProvider (private val repo: VendorRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VendorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VendorViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}