package com.example.e_glicemia.ui.glicemia

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.e_glicemia.R
import com.example.e_glicemia.data.glicemia.model.GlicemiaModel
import com.example.e_glicemia.util.Utilities
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_glicemia.*

class GlicemiaActivity() : AppCompatActivity() {
    private var db: FirebaseFirestore? = null
    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glicemia)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        db = FirebaseFirestore.getInstance()

        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("glicemiaId")

            Log.i("GlicemiaActivity", "id = " + id)
            val docRef = db!!.collection("glicemias").document(id.toString())
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val glicemia = documentSnapshot.toObject(GlicemiaModel::class.java)
                if (glicemia != null) {
                    Log.d("GlicemiaActivity", "valor = " + glicemia.valor)
                    editTextDateGlicemia.setText(glicemia.data)
                    editTextTimeGlicemia.setText(glicemia.hora)
                    editTextNumberGlicemia.setText(glicemia.valor.toString())
                } else {
                    Log.d("GlicemiaActivity", "glicemia null")
                }
            }
        } else {
            Log.d("GlicemiaActivity", "bundle null")
        }

        buttonGlicemiaAdicionar.setOnClickListener {
            if (editTextDateGlicemia.text.isNullOrEmpty()
                || editTextTimeGlicemia.text.isNullOrEmpty()
                || editTextNumberGlicemia.text.isNullOrEmpty()) {
                Toast.makeText(this@GlicemiaActivity, "Favor preencher todos os campos!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val data = editTextDateGlicemia.text.toString()
            val hora = editTextTimeGlicemia.text.toString()
            if (Utilities.isNumber(editTextNumberGlicemia.text.toString()) == false) {
                Toast.makeText(this@GlicemiaActivity, "Glicemia em formato inválido!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val valor = editTextNumberGlicemia.text.toString().toInt()

            if (Utilities.isValid(data) == false) {
                Toast.makeText(this@GlicemiaActivity, "Data em formato inválido!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (Utilities.isValidTime(hora) == false) {
                Toast.makeText(this@GlicemiaActivity, "Hora em formato inválido!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //valor.adicionar(data, hora, valor)
            if (id != null && id!!.isNotEmpty()) {
                Log.d("GlicemiaActivity", "Updating glicemia!")
                update(id!!, data, hora, valor)
            } else {
                Log.d("GlicemiaActivity", "Glicemia salva om sucesso!")
                adicionar(data, hora, valor)
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun adicionar(data: String, hora: String, valor: Int) {
        val currentUser = Firebase.auth.currentUser!!

        val glicemia = GlicemiaModel(
            userId = currentUser.uid,
            userName = currentUser.displayName,
            data = data,
            hora = hora,
            valor = valor
        )

        db!!.collection("glicemias")
            .add(glicemia)
            .addOnSuccessListener {
                Toast.makeText(this@GlicemiaActivity, "Inserido com sucesso ", Toast.LENGTH_LONG).show()
                //startActivity(Intent(this@GlicemiaActivity, MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this@GlicemiaActivity, "Falha ao adicionar", Toast.LENGTH_SHORT).show()
            }
    }
    private fun update(id: String, data: String, hora: String, valor: Int) {
        val currentUser = Firebase.auth.currentUser!!
        val glicemia = GlicemiaModel(
            userId = currentUser.uid,
            userName = currentUser.displayName,
            data = data,
            hora = hora,
            valor = valor
        )

        db!!.collection("glicemias")
            .document(id)
            .set(glicemia)
            .addOnSuccessListener {
                Log.e("GlicemiaActivity", "Glicemia atualizada com sucesso!")
                Toast.makeText(applicationContext, "Glicemia atualizada com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e("GlicemiaActivity", "Erro ao atualizar glicemia", e)
                Toast.makeText(applicationContext, "Erro ao atualizar glicemia", Toast.LENGTH_SHORT).show()
            }
    }

}