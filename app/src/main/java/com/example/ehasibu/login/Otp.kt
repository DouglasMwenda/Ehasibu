package com.example.ehasibu.login

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.ehasibu.R

class Otp : Fragment() {

    companion object {
        fun newInstance() = Otp()
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
        var view=inflater.inflate(R.layout.fragment_otp, container, false)

        view.findViewById<Button>(R.id.verify_otp_button).setOnClickListener{view.findNavController().navigate(R.id.Login)}

        view.findViewById<Button>(R.id.verify_otp_button).setOnClickListener{view.findNavController().navigate(R.id.Dashboard)}

        return view
    }
}