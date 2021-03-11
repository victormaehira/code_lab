package com.example.e_glicemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.e_glicemia.model.Glicemia
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_glicemia.*

class GlicemiaActivity() : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glicemia)

        buttonGlicemiaAdicionar.setOnClickListener {
            val data = editTextDateGlicemia.text.toString()
            val hora = editTextTimeGlicemia.text.toString()
            val valor = editTextNumberGlicemia.text.toString().toInt()

            valor.adicionar(data, hora, valor)
        }
    }


    private fun Int.adicionar(data: String, hora: String, valor: Number) {
        val currentUser = Firebase.auth.currentUser!!

        val glicemia = Glicemia(userId = currentUser.uid, userName = currentUser.displayName, data = data, hora = hora, valor = valor)

        db.collection("glicemias")
            .add(glicemia)
            .addOnSuccessListener {
                Toast.makeText(this@GlicemiaActivity, "Inserido com sucesso ", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@GlicemiaActivity, MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this@GlicemiaActivity, "Falha ao adicionar", Toast.LENGTH_SHORT).show()
            }
    }

}