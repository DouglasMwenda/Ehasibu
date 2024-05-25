package com.example.ehasibu.signup

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentSignUpBinding

class SignUp : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SignUp()
    }

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        setupFocusChangeListeners()

        binding.registerBtn.setOnClickListener {
            if (validateInput()) {
                // If input is valid, navigate to the login
                view.findNavController().navigate(R.id.action_signUp_to_Login)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupFocusChangeListeners() {
        binding.businessNameInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val businessName = binding.businessNameInput.text.toString().trim()
                if (businessName.isEmpty()) {
                    binding.businessNameInput.error = getString(R.string.error_empty_business_name)
                } else {
                    binding.businessNameInput.error = null
                }
            }
        }

        binding.firstNameInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val firstName = binding.firstNameInput.text.toString().trim()
                if (firstName.isEmpty()) {
                    binding.firstNameInput.error = getString(R.string.error_empty_first_name)
                } else {
                    binding.firstNameInput.error = null
                }
            }
        }

        binding.lastNameInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val lastName = binding.lastNameInput.text.toString().trim()
                if (lastName.isEmpty()) {
                    binding.lastNameInput.error = getString(R.string.error_empty_last_name)
                } else {
                    binding.lastNameInput.error = null
                }
            }
        }

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
        val businessName = binding.businessNameInput.text.toString().trim()
        val firstName = binding.firstNameInput.text.toString().trim()
        val lastName = binding.lastNameInput.text.toString().trim()
        val otherNames = binding.otherNamesInput.text.toString().trim()
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val confirmPassword = binding.confirmPasswordInput.text.toString().trim()

        if (businessName.isEmpty()) {
            binding.businessNameInput.error = getString(R.string.error_empty_business_name)
            binding.businessNameInput.requestFocus()
            return false
        }

        if (firstName.isEmpty()) {
            binding.firstNameInput.error = getString(R.string.error_empty_first_name)
            binding.firstNameInput.requestFocus()
            return false
        }

        if (lastName.isEmpty()) {
            binding.lastNameInput.error = getString(R.string.error_empty_last_name)
            binding.lastNameInput.requestFocus()
            return false
        }

        if (otherNames.isEmpty()) {
            binding.otherNamesInput.error = getString(R.string.error_empty_other_name)
            binding.otherNamesInput.requestFocus()
            return false
        }

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
