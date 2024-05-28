package com.example.ehasibu.login.model

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentLoginBinding

class Login : Fragment() {
    companion object {
        fun newInstance() = Login()
    }

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
//            val email = binding.emailInput.text.toString().trim()
//            val password = binding.passwordInput.text.toString().trim()
//
//            viewModel.login(email, password)
//        }
//
//        viewModel.loginResult.observe(viewLifecycleOwner, Observer { response ->
//            if (response.isSuccessful) {
//                // Navigate to OTP screen or show success message
//                Log.d("Login", "Login successful: ${response.body()?.message}")
//            } else {
//                // Show error message
//                Log.e("Login", "Login failed: ${response.code()}")
//            }
//        })

            binding.loginBtn.setOnClickListener {
                if (validateInput()) {
                    val email = binding.emailInput.text.toString().trim()
                    val password = binding.passwordInput.text.toString().trim()

                    viewModel.login(email, password, { message ->
                        // Handle success, navigate to OTP screen
                        view.findNavController().navigate(R.id.action_login_to_otp)
                    }, { errorMessage ->
                        // Handle error
                        binding.emailInput.error = errorMessage
                    })
                }
            }

            binding.signupBtn.setOnClickListener {
                view.findNavController().navigate(R.id.action_login_to_password_Reset)
            }

        }
    }

            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
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

            if (!isValidPassword(password)) {
                binding.passwordInput.error = getString(R.string.error_weak_password)
                binding.passwordInput.requestFocus()
                return false
            }

            return true
        }

        private fun isValidPassword(password: String): Boolean {
            val passwordPattern =
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$%*?&])[A-Za-z\\d@\$%*?&]{6,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.matches(password)
        }
    }



