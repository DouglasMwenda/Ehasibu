package com.example.ehasibu.login.model

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.AppModule
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentLoginBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.data.AuthUserResponse
import com.example.ehasibu.login.data.UserRequest
import com.example.ehasibu.login.viewmodel.LoginViewModel
import com.example.ehasibu.utils.LOGIN_EMAIL
import com.example.ehasibu.utils.LOGIN_PASSWORD
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "Login"

class Login : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var pref: SharedPreferences
    private lateinit var prefEditor: SharedPreferences.Editor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        pref = requireActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        prefEditor = pref.edit()

        clearLoginFields()

        binding.loginBtn.setOnClickListener {
            if (validateInput()) {
                val email = binding.emailInput.text.toString().trim()
                val password = binding.passwordInput.text.toString().trim()
                userLogin(requireContext(), email, password, binding.loginBtn)
            }
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_forgotPass)
        }

        return binding.root
    }

    private fun clearLoginFields() {
        binding.emailInput.setText("")
        binding.passwordInput.setText("")
    }

    private fun validateInput(): Boolean {
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        if (email.isEmpty()) {
            binding.emailInput.error = getString(R.string.error_empty_email)
            binding.emailInput.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.error = getString(R.string.error_invalid_email)
            binding.emailInput.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.error_empty_password)
            binding.passwordInput.requestFocus()
            return false
        }

        return true
    }

    private fun userLogin(cont: Context, email: String, password: String, loginBtn: Button) {
        val ret = AppModule().getRetrofitInstance("")
        val req = ret.login(UserRequest(email.trim(), password.trim()))
        req.enqueue(object : Callback<ApiResponse<AuthUserResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<AuthUserResponse>>,
                response: Response<ApiResponse<AuthUserResponse>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val navController = loginBtn.findFragment<Login>().findNavController()
                        when (response.body()!!.statusCode) {
                            403 -> {
                                prefEditor.putString(LOGIN_EMAIL, email.trim()).apply()
                                prefEditor.putString(LOGIN_PASSWORD, password.trim()).apply()
                                Log.d(TAG, "Navigating to Password Reset")
                                navController.navigate(R.id.action_login_to_password_Reset)
                            }
                            200 -> {
                                prefEditor.putString(LOGIN_EMAIL, email.trim()).apply()
                                val message = response.body()?.message ?: "null body..."
                                Log.d(TAG, message)
                                Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                                Log.d(TAG, "Navigating to OTP")
                                if (navController.currentDestination?.id == R.id.login) {
                                    Log.d(TAG, "Current destination is login")
                                    navController.navigate(R.id.action_login_to_otp, null)
                                } else {
                                    Log.e(TAG, "Current destination is not login")
                                }
                            }
                            else -> {
                                val message = response.body()!!.message
                                Log.d(TAG, message)
                                Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        val message = response.toString()
                        Log.d(TAG, message)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<AuthUserResponse>>, t: Throwable) {
                Log.d(TAG, t.message!!)
            }
        })
    }
}
