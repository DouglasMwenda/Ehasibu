package com.example.ehasibu.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ehasibu.R

class Login : Fragment() {

    /*private  var email: String?= null
    private  var password: String?= null*/
    companion object {
        fun newInstance() = Login()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        var view=inflater.inflate(R.layout.fragment_login,container, false)

        view.findViewById<Button>(R.id.login_btn).setOnClickListener{view.findNavController().navigate(R.id.action_login_to_otp)}
        view.findViewById<Button>(R.id.signup_btn).setOnClickListener{view.findNavController().navigate(R.id.action_login_to_signUp)}
        return  view
    }

    fun Login(view: View) {}

}