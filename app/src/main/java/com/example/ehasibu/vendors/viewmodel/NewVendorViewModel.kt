package com.example.ehasibu.vendors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.vendors.moddel.AddRequest
import com.example.ehasibu.vendors.moddel.EditVRequest
import com.example.ehasibu.vendors.moddel.Entity
import com.example.ehasibu.vendors.moddel.VendorRepo
import kotlinx.coroutines.launch

class NewVendorViewModel(private val repository: VendorRepo) : ViewModel() {
    private val _isVendorAdded = MutableLiveData<Boolean>()
    val isVendorAdded: LiveData<Boolean> get() = _isVendorAdded

    private val _vendor = MutableLiveData<Entity>()
    val vendor: LiveData<Entity> get() = _vendor

    fun addVendor(vendor: AddRequest) {
        viewModelScope.launch {
            try {
                val response = repository.addVendor(vendor)
                if (response.isSuccessful) {
                    _isVendorAdded.value = true
                } else {
                    _isVendorAdded.value = false
                }
            } catch (e: Exception) {
                _isVendorAdded.value = false
            }

        }
    }

    fun updateVendor( vendor: EditVRequest) {
        viewModelScope.launch {
            try {
                val response = repository.editVendor(vendor)
                if (response.isSuccessful) {
                    response.body()?.let { editResponse ->
                        _vendor.value = editResponse.entity
                    }
                } else {
                    println("Error: ${response.errorBody()}")
                }
            }
            catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    class AddVProvider(private val repository: VendorRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewVendorViewModel(repository) as T

        }
    }
}