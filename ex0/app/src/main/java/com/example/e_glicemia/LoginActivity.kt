package com.example.e_glicemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
//import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    //private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var auth = Firebase.auth

        textViewLoginCadastrar.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        buttonLoginEntrar.setOnClickListener {
            val email = editTextLoginEmailAddress.text.toString()
            val password = editTextLoginPassword.text.toString()

            Toast.makeText(this, "Login Tapped", Toast.LENGTH_SHORT).show()

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falha ao realizar login", Toast.LENGTH_SHORT).show()
                }
        }
    }
}