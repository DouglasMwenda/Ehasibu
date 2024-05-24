package com.example.ehasibu
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.ehasibu.R

class MainActivity : AppCompatActivity() {

   /* lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button*/
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_login)

      /*  emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            Log.i("credentials test", "email: $email and password : $password")

            // Here you can add code to perform login authentication
            // For simplicity, I'm assuming login is successful and navigating to the next screen
            val navController = findNavController(R.id.nav_graph)
            navController.navigate(R.id.action_login_to_otp)
        }

        val navigationButton: Button = findViewById(R.id.signup_btn)
        navigationButton.setOnClickListener {
            // Navigate to the SignUpFragment using the Navigation Component
            val navController = findNavController(R.id.nav_graph)
           // navController.navigate(R.id.action_signup_to_login)
        }*/
}
}

