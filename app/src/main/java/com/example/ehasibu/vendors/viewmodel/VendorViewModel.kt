package com.example.ehasibu.vendors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.vendors.moddel.Entity
import com.example.ehasibu.vendors.moddel.VendorRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class VendorViewModel(private val repo: VendorRepo) : ViewModel() {
    private val _vendors = MutableLiveData<List<Entity>?>()
    val vendors: LiveData<List<Entity>?> get() = _vendors


    init {

        fetchVendors()
    }

    private fun fetchVendors() {
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

                    delay(1000)

                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }


        }

    }

    fun deleteVendor(vendorId: String) {
        viewModelScope.launch {
            try {
                val response = repo.deleteVendor(vendorId)
                if (response.isSuccessful) {
                    val updatedVendors = _vendors.value?.filterNot { it.vendorId == vendorId }
                    _vendors.value = updatedVendors
                } else {
                    println("Error: ${response.errorBody()}")
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }


}


class VendorProvider(private val repo: VendorRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VendorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VendorViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}