package com.example.ehasibu.signup.view_model
//
//import android.app.Application
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.ehasibu.login.data.User
//import com.example.ehasibu.login.repository.AuthRepository
//
//class SignUpViewModel(val authRepository: AuthRepository, val application: Application) :
//    ViewModel() {
//    private var user: MutableLiveData<User> = MutableLiveData()
//    private var errorMessage: MutableLiveData<HashMap<String, String>> = MutableLiveData()
//    private var isUnique: MutableLiveData<Boolean> =
//        MutableLiveData<Boolean>().apply { value = false }
//    private var isLoading: MutableLiveData<Boolean> =
//        MutableLiveData<Boolean>().apply { value = false }
//
//
//    fun getErrorMessage(): LiveData<HashMap<String, String>> = errorMessage
//    fun getIsLoading(): LiveData<Boolean> = isLoading
//    fun getIsUnique(): LiveData<Boolean> = isUnique
//    fun getUser(): LiveData<User> = user
//
//    fun validateEmailAddres(body: ValidateEmail) {
//
//    }
//}
//
//
//
//
//
