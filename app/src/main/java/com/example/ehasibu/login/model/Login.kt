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
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentLoginBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.api.APIService
import com.example.ehasibu.login.data.AuthUserResponse
import com.example.ehasibu.login.data.UserRequest
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        pref = requireActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        prefEditor = pref.edit()

        val cont = requireContext()
        binding.loginBtn.setOnClickListener {
            if (validateInput()) {

                val email = binding.emailInput.text.toString().trim()
                val password = binding.passwordInput.text.toString().trim()

                userLogin(cont, email, password, binding.loginBtn)

            }

        }


        return binding.root
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
        val ret = APIService.instance

        val req = ret.login(UserRequest(email.trim(), password.trim()))
        req.enqueue(object : Callback<ApiResponse<AuthUserResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<AuthUserResponse>>,
                response: Response<ApiResponse<AuthUserResponse>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (response.body()!!.statusCode == 403) {
                            prefEditor.putString(LOGIN_EMAIL, email.trim()).apply()
                            prefEditor.putString(LOGIN_PASSWORD, password.trim()).apply()
                            loginBtn.findFragment<Login>().findNavController()
                                .navigate(R.id.action_login_to_password_Reset)
                        } else if (response.body()!!.statusCode == 200) {
                            val message = response.toString()
                            Log.d(TAG, message)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()

                            loginBtn.findFragment<Login>().findNavController()
                                .navigate(R.id.action_login_to_otp)

                        }else{
                            val message = response.body()!!.message
                            Log.d(TAG, message)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        val message = response.toString()
                        Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, message)

                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<AuthUserResponse>>, t: Throwable) {
               // Toast.makeText(cont, t.message, Toast.LENGTH_SHORT).show()
                Log.d(TAG, t.message!!)

            }

        })
    }

}



