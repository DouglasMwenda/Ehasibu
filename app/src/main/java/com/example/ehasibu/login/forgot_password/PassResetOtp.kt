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
import com.example.ehasibu.databinding.FragmentPassResetOtpBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.utils.api.APIService
import com.example.ehasibu.login.data.OtpRequest2
import com.example.ehasibu.login.model.Otp
import com.example.ehasibu.login.viewmodel.PassResetOtpViewModel
import com.example.ehasibu.utils.LOGIN_EMAIL
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ForgotpOtp"

class PassResetOtp : Fragment() {
    private lateinit var binding: FragmentPassResetOtpBinding
    private lateinit var pref: SharedPreferences
    private lateinit var prefEditor: SharedPreferences.Editor


    private val viewModel: PassResetOtpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {

        binding = FragmentPassResetOtpBinding.inflate(inflater, container, false)

        pref = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        prefEditor = pref.edit()

        binding.validateOtpButton.setOnClickListener {
            if (validateOtp()) {


                val cont = requireContext()

                val email = pref.getString(LOGIN_EMAIL, "")

                val otp = binding.otpInput.text.toString().trim()

                    otpValidation(cont, email!!, otp, binding.validateOtpButton)

            }
        }
        return binding.root
    }

    private fun validateOtp(): Boolean {
        val otp = binding.otpInput.text.toString().trim()

        if (otp.isEmpty()) {
            binding.otpInput.error = getString(R.string.error_empty_otp)
            binding.otpInput.requestFocus()
            return false
        }

        if (otp.length!= 6) {
            binding.otpInput.error = getString(R.string.error_invalid_otp)
            binding.otpInput.requestFocus()
            return false
        }

        return true
    }

    private fun otpValidation(cont: Context,  email: String, Otp: String, validateOtpButton: Button) {
        val apiService = APIService.instance
        val request = apiService.otpValidate(OtpRequest2(email.trim(),Otp))

            request.enqueue(object : Callback<ApiResponse<OtpValResponse>> {
                override fun onResponse(
                    call: Call<ApiResponse<OtpValResponse>>,
                    response: Response<ApiResponse<OtpValResponse>>
                ) {
                    if (response.isSuccessful) {
                        val OtpValResponse = response.body()

                        if (OtpValResponse?.statusCode == 200) {
                            prefEditor.putString(LOGIN_EMAIL, email.trim()).apply()

                            val message = response.toString()
                            Log.d(TAG, message)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                            validateOtpButton.findFragment<Otp>().findNavController().navigate(
                                R.id.action_passResetOtp2_to_passReset
                            )


                        } else {
                            val message = response.toString()
                            Log.d(TAG, message)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        val message = response.toString()
                        Log.d(TAG, message)
                        Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse<OtpValResponse>>, t: Throwable) {

                    Log.d(TAG, t.message!!)
                }
            })
        }

        private fun navigateToOtpVerificationScreen(email: String) {
            // Implement the navigation logic to OTP verification screen
        }
        
    }
