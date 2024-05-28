package com.example.ehasibu.login.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentOtpBinding

class Otp : Fragment() {

    companion object {
        fun newInstance() = Otp()
    }

    private val viewModel: OtpViewModel by viewModels()
    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!

    //(In real applications, this should come from the server)
    private val correctOtp = "123456"

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

                // If OTP is correct, navigate to the dashboard
                view.findNavController().navigate(R.id.dashboard)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

        if (otp != correctOtp) {
            binding.otpInput.error = getString(R.string.error_incorrect_otp)
            binding.otpInput.requestFocus()
            return false
        }

        return true
    }
}
