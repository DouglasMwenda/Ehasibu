package com.example.ehasibu.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ehasibu.MainActivity
import com.example.ehasibu.R

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_sign_up)

        val navigationButton: Button = findViewById(R.id.register_btn)
        navigationButton.setOnClickListener {
            val intent = Intent(this,MainActivity ::class.java)
            startActivity(intent)
        }
    }
}
