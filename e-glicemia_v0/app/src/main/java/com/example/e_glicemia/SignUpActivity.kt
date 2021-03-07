package com.example.e_glicemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonSignUpSair.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        buttonSignUpSalvar.setOnClickListener {
            val nome = editTextSignUpPersonName.text.toString()
            val email = editTextSignUpEmailAddress.text.toString()
            val password = editTextSignUpPassword.text.toString()

            Toast.makeText(this, "Login Tapped", Toast.LENGTH_SHORT).show()
        }

    }
}