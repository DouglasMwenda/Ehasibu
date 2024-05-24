package com.example.ehasibu.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ehasibu.MainActivity
import com.example.ehasibu.R
import com.example.ehasibu.login.Otp
import com.example.ehasibu.login.OtpViewModel

class SignUp : Fragment() {
    companion object {
        fun newInstance() = SignUp()
    }

    private val viewModel: OtpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     var view =inflater.inflate(R.layout.fragment_sign_up, container, false)

        view.findViewById<Button>(R.id.register_btn).setOnClickListener{view.findNavController().navigate(R.id.otp)}

            return view
    }
}


