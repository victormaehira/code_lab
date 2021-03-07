package com.example.e_glicemia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLoginEntrar.setOnClickListener {
            val email = editTextLoginEmailAddress.text.toString()
            val password = editTextLoginPassword.text.toString()

            Toast.makeText(this, "Login Tapped", Toast.LENGTH_SHORT).show()
        }
    }
}