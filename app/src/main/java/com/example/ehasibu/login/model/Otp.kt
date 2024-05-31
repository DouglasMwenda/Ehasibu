package com.example.ehasibu.login.model

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentOtpBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.api.APIService
import com.example.ehasibu.login.data.AuthUserResponse
import com.example.ehasibu.login.data.OtpRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
private val TAG = "OtpVerification"
class Otp : Fragment() {

    companion object {
        fun newInstance() = Otp()
    }

    private val viewModel: OtpViewModel by viewModels()

    private lateinit var _binding: FragmentOtpBinding

    private val binding get() = _binding!!

    //(In real applications, this should come from the server)
    //private val correctOtp = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.verifyOtpButton.setOnClickListener {
            if (validateOtp()) {

                val cont = requireContext()

                val otp = binding.otpInput.text.toString().trim()


           //     otpverification(cont,  otp,_binding.verifyOtpButton)
                // If OTP is correct, navigate to the dashboard
              //  view.findNavController().navigate(R.id.dashboard)
            }
        }
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

//        if (otp != correctOtp) {
//            binding.otpInput.error = getString(R.string.error_incorrect_otp)
//            binding.otpInput.requestFocus()
//            return false
//        }

        return true
    }


//    private fun otpverification(cont: Context, email: String, otp: String) {
//        val ret = APIService.instance
//
//        val req = ret.otpVerify(OtpRequest(otp, email.trim()))
//        req.enqueue(object : Callback<ApiResponse<AuthUserResponse>> {
//            override fun onResponse(
//                call: Call<ApiResponse<AuthUserResponse>>,
//                response: Response<ApiResponse<AuthUserResponse>>
//            ) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.statusCode == 200) {
//
//                            verifyOtpButton.findFragment<Login>().findNavController()
//                                .navigate(R.id.action_otp_to_dashboard)
//
//                        } else {
//                            val message = response.body()!!.message
//                            Log.d(TAG, message)
//                            Toast.makeText(cont, message, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse<AuthUserResponse>>, t: Throwable) {
//                // Toast.makeText(cont, t.message, Toast.LENGTH_SHORT).show()
//                Log.d(TAG, t.message!!)
//
//            }
//
//        })
//    }

}