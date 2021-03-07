package com.example.e_glicemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val auth = Firebase.auth

        buttonSignUpSair.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        buttonSignUpSalvar.setOnClickListener {
            val name = editTextSignUpPersonName.text.toString()
            val email = editTextSignUpEmailAddress.text.toString()
            val password = editTextSignUpPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val currentUser = auth.currentUser

                    val userProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(name).build()

                    currentUser!!.updateProfile(userProfileChangeRequest)
                        .addOnCompleteListener {
                            //startActivity(Intent(this, ChatActivity::class.java))
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falha ao realizar cadastro", Toast.LENGTH_SHORT).show()
                }
        }

    }
}