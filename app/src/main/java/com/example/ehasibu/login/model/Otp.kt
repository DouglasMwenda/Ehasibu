package com.example.ehasibu.login.model

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.AppModule
import com.example.ehasibu.MainActivity
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentOtpBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.data.AuthUserResponse
import com.example.ehasibu.login.data.OtpRequest
import com.example.ehasibu.login.viewmodel.OtpViewModel
import com.example.ehasibu.utils.API_TOKEN
import com.example.ehasibu.utils.LOGIN_EMAIL
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "otp"
class Otp : Fragment() {



    private val viewModel: OtpViewModel by viewModels()

    private lateinit var binding: FragmentOtpBinding
    private lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        Log.d(TAG, "onViewCreated called")

        (activity as? MainActivity)?.hideBottomNavigationView()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentOtpBinding.inflate(inflater, container, false)
        pref = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        binding.verifyOtpButton.setOnClickListener {

            if (validateOtp()) {

                val cont = requireContext()

                val otp = binding.otpInput.text.toString().trim()
                Log.d(TAG, "otp: $otp")

                otpverification(cont, otp, binding.verifyOtpButton)

            }
        }

        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView called")

        (activity as? MainActivity)?.showBottomNavigationView()
    }

    private fun validateOtp(): Boolean {
        val otp = binding.otpInput.text.toString().trim()

        if (otp.isEmpty()) {
            binding.otpInput.error = getString(R.string.error_empty_otp)
            binding.otpInput.requestFocus()
            return false
        }

        if (otp.length != 6) {
            binding.otpInput.error = getString(R.string.error_invalid_otp)
            binding.otpInput.requestFocus()
            return false
        }

        return true
    }


    private fun otpverification(cont: Context, otp: String, verifyOtpButton: Button) {
        val ret = AppModule().getRetrofitInstance("")
        val email = pref.getString(LOGIN_EMAIL, "")
        val req = ret.otpVerify(OtpRequest(otp, email!!.trim()))
        Log.d(TAG, email)
        req.enqueue(object : Callback<ApiResponse<AuthUserResponse>> {
            override fun onResponse(
                call: Call<ApiResponse<AuthUserResponse>>,
                response: Response<ApiResponse<AuthUserResponse>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (response.body()!!.statusCode == 200) {

                            val message = response.body()!!.message

                            pref.edit().putString(API_TOKEN, response.body()!!.entity.access_token).apply()

                            Log.d(TAG, response.body()!!.entity.access_token)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                            verifyOtpButton.findFragment<Login>().findNavController()
                                .navigate(R.id.action_otp_to_dashboard,
                                    null,
                                    NavOptions.Builder().setPopUpTo(R.id.otp, true).build())

                        } else {
                            val message = response.body()!!.message
                            Log.d(TAG, message)
                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{

                    val message = response.toString()
                    Log.d(TAG, message)
                }
            }

            override fun onFailure(call: Call<ApiResponse<AuthUserResponse>>, t: Throwable) {
                 Toast.makeText(cont, t.message, Toast.LENGTH_SHORT).show()
                Log.d(TAG, t.message!!)

            }

        })
    }

}