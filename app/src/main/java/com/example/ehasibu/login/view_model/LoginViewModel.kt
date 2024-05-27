package com.example.ehasibu.login.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.ehasibu.dashboard.Dashboard
import com.example.ehasibu.login.repository.AuthRepository

class LoginViewModel (val authRepository:AuthRepository, val application: Application): ViewModel() {
    // TODO: Implement the ViewModel

    val fragment = Dashboard()


}