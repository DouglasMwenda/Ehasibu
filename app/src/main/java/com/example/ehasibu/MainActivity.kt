package com.example.ehasibu
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.ehasibu.R

class MainActivity : AppCompatActivity() {

   private lateinit var navController: NavController
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
        return navController.navigateUp()|| super.onSupportNavigateUp()
    }
}
