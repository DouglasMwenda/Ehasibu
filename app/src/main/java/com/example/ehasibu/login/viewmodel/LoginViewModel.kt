package com.example.ehasibu.login.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val res=mutableStateOf<String>("")

//    fun login(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
//        viewModelScope.launch {
//            try {
//                val Retrofit= APIService.instance
//                val response: Response<AuthUserResponse> = Retrofit.login(UserRequest(email, password))
//
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        res.value=it.access_token
//
//
//                    } ?: run {
//                        onError("Response body is null")
//                    }
//                } else {
//                    onError("Error: ${response.code()}")
//                }
//            } catch (e: Exception) {
//                onError("Exception: ${e.message}")
//            }
//        }
//    }
}
