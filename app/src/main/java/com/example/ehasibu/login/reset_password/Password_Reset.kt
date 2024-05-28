package com.example.ehasibu.login.reset_password

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentPasswordResetBinding

class Password_Reset : Fragment() {

    private var _binding: FragmentPasswordResetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PasswordResetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordResetBinding.inflate(inflater, container, false)
        val view = binding.root

        setupFocusChangeListeners()

        binding.registerBtn.setOnClickListener {
            if (validateInput()) {
                // If input is valid, navigate to the login
                view.findNavController().navigate(R.id.action_password_Reset_to_login)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupFocusChangeListeners() {

        binding.otherNamesInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val otherNames = binding.otherNamesInput.text.toString().trim()
                if (otherNames.isEmpty()) {
                    binding.otherNamesInput.error = getString(R.string.error_empty_other_name)
                } else {
                    binding.otherNamesInput.error = null
                }
            }
        }

        binding.emailInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email = binding.emailInput.text.toString().trim()
                when {
                    email.isEmpty() -> {
                        binding.emailInput.error = getString(R.string.error_empty_email)
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        binding.emailInput.error = getString(R.string.error_invalid_email)
                    }
                    else -> {
                        binding.emailInput.error = null
                    }
                }
            }
        }

        binding.passwordInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val password = binding.passwordInput.text.toString().trim()
                when {
                    password.isEmpty() -> {
                        binding.passwordInput.error = getString(R.string.error_empty_password)
                    }
                    password.length < 6 -> {
                        binding.passwordInput.error = getString(R.string.error_short_password)
                    }
                    !isValidPassword(password) -> {
                        binding.passwordInput.error = getString(R.string.error_weak_password)
                    }
                    else -> {
                        binding.passwordInput.error = null
                    }
                }
            }
        }

        binding.confirmPasswordInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val confirmPassword = binding.confirmPasswordInput.text.toString().trim()
                when {
                    confirmPassword.isEmpty() -> {
                        binding.confirmPasswordInput.error = getString(R.string.error_empty_confirm_password)
                    }
                    binding.passwordInput.text.toString().trim() != confirmPassword -> {
                        binding.confirmPasswordInput.error = getString(R.string.error_password_mismatch)
                    }
                    else -> {
                        binding.confirmPasswordInput.error = null
                    }
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val confirmPassword = binding.confirmPasswordInput.text.toString().trim()

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

        if (confirmPassword.isEmpty()) {
            binding.confirmPasswordInput.error = getString(R.string.error_empty_confirm_password)
            binding.confirmPasswordInput.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            binding.confirmPasswordInput.error = getString(R.string.error_password_mismatch)
            binding.confirmPasswordInput.requestFocus()
            return false
        }

        return true
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{6,}$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.matches(password)
    }
}
