package com.example.ehasibu.login.forgot_password

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.example.ehasibu.databinding.FragmentPassResetBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.utils.api.APIService
import com.example.ehasibu.login.data.PassRequest
import com.example.ehasibu.login.model.Login
import com.example.ehasibu.login.reset_password.PasswordRequest
import com.example.ehasibu.login.viewmodel.PassResetViewModel
import com.example.ehasibu.utils.LOGIN_EMAIL
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "P_Reset"

class PassReset : Fragment() {

    private lateinit var pref: SharedPreferences

    private lateinit var binding: FragmentPassResetBinding

    private val viewModel: PassResetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPassResetBinding.inflate(inflater)
        pref = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)



        val view = binding.root

        setupFocusChangeListeners()
        val cont = requireContext()
        binding.passResetBtn.setOnClickListener {
            if (validateInput()) {
                val currentPassword = binding.currentPassInput.text.toString().trim()
                val confirmPassword = binding.confirmPassInput.text.toString().trim()
                val newPassword = binding.newPassInput.text.toString().trim()
                passwordReset(cont, currentPassword = currentPassword,
                    newPassword = newPassword, confirmPassword=confirmPassword, binding.passResetBtn)

            }
        }

        return view
    }

    private fun setupFocusChangeListeners() {


        binding.currentPassInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val currentPassword = binding.currentPassInput.text.toString().trim()
                when {
                    currentPassword.isEmpty() -> {
                        binding.currentPassInput.error = getString(R.string.error_empty_password)
                    }

                    else -> {
                        binding.currentPassInput.error = null
                    }
                }
            }
        }

        binding.newPassInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val newPassword = binding.newPassInput.text.toString().trim()
                when {
                    newPassword.isEmpty() -> {
                        binding.newPassInput.error = getString(R.string.error_empty_password)
                    }

                    else -> {
                        binding.newPassInput.error = null
                    }
                }
            }
        }

        binding.confirmPassInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val confirmPassword = binding.confirmPassInput.text.toString().trim()
                when {
                    confirmPassword.isEmpty() -> {
                        binding.confirmPassInput.error = getString(R.string.error_empty_confirm_password)
                    }
                    binding.newPassInput.text.toString().trim() != confirmPassword -> {
                        binding.confirmPassInput.error = getString(R.string.error_password_mismatch)
                    }
                    else -> {
                        binding.confirmPassInput.error = null
                    }
                }
            }
        }
    }

    private fun validateInput(): Boolean {

        val currentPassword = binding.currentPassInput.text.toString().trim()
        val newPassword = binding.newPassInput.text.toString().trim()
        val confirmPassword = binding.confirmPassInput.text.toString().trim()



        if (currentPassword.isEmpty()) {
            binding.currentPassInput.error = getString(R.string.error_empty_password)
            binding.currentPassInput.requestFocus()
            return false
        }

        if (newPassword.isEmpty()) {
            binding.newPassInput.error = getString(R.string.error_empty_password)
            binding.newPassInput.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            binding.confirmPassInput.error = getString(R.string.error_empty_confirm_password)
            binding.confirmPassInput.requestFocus()
            return false
        }

        if (newPassword != confirmPassword) {
            binding.confirmPassInput.error = getString(R.string.error_password_mismatch)
            binding.confirmPassInput.requestFocus()
            return false
        }

        return true
    }

    private fun passwordReset(
        cont: Context,
        currentPassword: String,
        newPassword: String,
        confirmPassword: String,
        passwordResetBtn: Button,) {
        val ret = APIService.instance

        val logEmail = pref.getString(LOGIN_EMAIL, "")

        Log.d(TAG, logEmail.toString())

        val req = ret.passwordReset(PassRequest(email =  logEmail.toString(),
            confirmPassword = confirmPassword.trim(),
            newPassword = newPassword.trim(),
            currentPassword = currentPassword.trim()))

        req.enqueue(object : Callback<ApiResponse<PasswordRequest>> {
            override fun onResponse(
                call: Call<ApiResponse<PasswordRequest>>,
                response: Response<ApiResponse<PasswordRequest>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (response.body()!!.statusCode==200){
                            val message = response.body()!!.message
                            Log.d(TAG, message)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                            passwordResetBtn.findFragment<Login>().findNavController().navigate(R.id. action_passReset_to_login)
                        }
                    }else{
                        val message = response.toString()
                        Log.d(TAG, message)
                        Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val message = response.toString()
                    Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, message)

                }
            }

            override fun onFailure(call: Call<ApiResponse<PasswordRequest>>, t: Throwable) {
                // Toast.makeText(cont, t.message, Toast.LENGTH_SHORT).show()
                Log.d(TAG, t.message!!)

            }

        })
    }
}
