package com.example.e_glicemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import com.example.e_glicemia.model.Glicemia

class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = Firebase.auth
        val userName = auth.currentUser!!.displayName

        textViewMainName.text = "Olá, $userName"

        imageButtonMainAdcionaGlicemia.setOnClickListener{
            startActivity(Intent(this, GlicemiaActivity::class.java))
        }

    }


}