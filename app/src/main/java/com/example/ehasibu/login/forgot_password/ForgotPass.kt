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
import com.example.ehasibu.databinding.FragmentForgotPassBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.api.APIService
import com.example.ehasibu.login.data.PassResetRequest
import com.example.ehasibu.login.model.Otp
import com.example.ehasibu.utils.LOGIN_EMAIL
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "Forgotpass"

class ForgotPass : Fragment() {


    private val viewModel: ForgotPassViewModel by viewModels()
    private lateinit var binding: FragmentForgotPassBinding
    private lateinit var pref: SharedPreferences
    private lateinit var prefEditor: SharedPreferences.Editor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentForgotPassBinding.inflate(inflater)

        pref = requireActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        prefEditor = pref.edit()
        val cont = requireContext()
        binding.forgotPasswordBtn.setOnClickListener {


                val email = binding.emailInput.text.toString().trim()


                sendOtp(cont, email, binding.forgotPasswordBtn)



        }

        return binding.root
    }

    private fun sendOtp(cont: Context, email: String, forgotPasswordBtn: Button) {
        val apiService = APIService.instance
        val request = apiService.passOtpSend(PassResetRequest(email.trim()))

        request.enqueue(object : Callback<ApiResponse<ForgotPassResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<ForgotPassResponse>>,
                response: Response<ApiResponse<ForgotPassResponse>>
            ) {
                if (response.isSuccessful) {
                    val forgotPassResponse = response.body()

                    if (forgotPassResponse?.statusCode== 200) {
                        prefEditor.putString(LOGIN_EMAIL, email.trim()).apply()

                        val message = response.toString()
                        Log.d(TAG, message)
                        Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()

                        forgotPasswordBtn.findFragment<Otp>().findNavController().navigate(
                               R.id.action_forgotPass_to_passResetOtp)


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

            override fun onFailure(call: Call<ApiResponse<ForgotPassResponse>>, t: Throwable) {

                Log.d(TAG, t.message!!)
            }
        })
    }

    private fun navigateToOtpVerificationScreen(email: String) {
        // Implement the navigation logic to OTP verification screen
    }

}