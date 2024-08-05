package com.example.ehasibu.login.reset_password

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
import androidx.navigation.fragment.findNavController
import com.example.ehasibu.AppModule
import com.example.ehasibu.MainActivity
import com.example.ehasibu.R
import com.example.ehasibu.databinding.FragmentPasswordResetBinding
import com.example.ehasibu.login.ApiResponse
import com.example.ehasibu.login.data.PassRequest
import com.example.ehasibu.login.model.Login
import com.example.ehasibu.utils.LOGIN_EMAIL
import com.example.ehasibu.utils.LOGIN_PASSWORD
import com.example.ehasibu.utils.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PassReset"

class Password_Reset : Fragment() {

    //val args: PasswordResetArgs? by navArgs()
    private lateinit var pref: SharedPreferences

    private lateinit var binding: FragmentPasswordResetBinding

    //private val viewModel: PasswordResetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.password)
        (activity as? MainActivity)?.hideBottomNavigationView()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordResetBinding.inflate(inflater)
        pref = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)



        val view = binding.root

        setupFocusChangeListeners()
        val cont = requireContext()
        binding.passwordResetBtn.setOnClickListener {
            if (validateInput()) {
                val password = binding.passwordInput.text.toString().trim()
                val confirmPassword = binding.confirmPasswordInput.text.toString().trim()

                passwordReset(cont,
                    newPassword = password, confirmPassword=confirmPassword, binding.passwordResetBtn)

            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.showBottomNavigationView()
    }

    private fun setupFocusChangeListeners() {


        binding.passwordInput.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val password = binding.passwordInput.text.toString().trim()
                when {
                    password.isEmpty() -> {
                        binding.passwordInput.error = getString(R.string.error_empty_password)
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

        val password = binding.passwordInput.text.toString().trim()
        val confirmPassword = binding.confirmPasswordInput.text.toString().trim()



        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.error_empty_password)
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

    private fun passwordReset(
        cont: Context,
        newPassword: String,
        confirmPassword: String,
        passwordResetBtn: Button,) {
        val ret = AppModule().getRetrofitInstance("")

        val logEmail = pref.getString(LOGIN_EMAIL, "")
        val logPass = pref.getString(LOGIN_PASSWORD, "")

        Log.d(TAG, logEmail.toString())
        Log.d(TAG, logPass.toString())

        val req = ret.passwordReset(PassRequest(email =  logEmail.toString(),
            confirmPassword = confirmPassword.trim(),
            newPassword = newPassword.trim(),
            currentPassword = logPass!!))

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
                            passwordResetBtn.findFragment<Login>().findNavController().navigate(R.id. action_password_Reset_to_login)
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
