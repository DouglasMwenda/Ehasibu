package com.example.ehasibu.login.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.login.data.EntityResponse
import com.example.ehasibu.login.data.User
import com.example.ehasibu.login.utils.APIService
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {

    fun login(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response: Response<EntityResponse> = APIService.instance.login(User(email, password))

                if (response.isSuccessful) {
                    response.body()?.let {

                    } ?: run {
                        onError("Response body is null")
                    }
                } else {
                    onError("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                onError("Exception: ${e.message}")
            }
        }
    }
}
