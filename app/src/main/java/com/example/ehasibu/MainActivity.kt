package com.example.ehasibu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ehasibu.signup.SignUp

class MainActivity : AppCompatActivity() {

lateinit var emailInput:EditText
lateinit var passwordInput:EditText
lateinit var loginBtn:Button
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.fragment_login)
        val navigationButton: Button = findViewById(R.id.signup_btn)
        navigationButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)

            startActivity(intent)

            }

            emailInput = findViewById(R.id.email_input)
            passwordInput = findViewById(R.id.password_input)
            loginBtn = findViewById(R.id.login_btn)

            loginBtn.setOnClickListener {
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()

                Log.i("credentials test", "email: $email and password : $password")


            }


        }
    }
