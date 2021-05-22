package com.example.e_glicemia.ui.alarme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.e_glicemia.R
import com.example.e_glicemia.data.alarme.model.AlarmeModel
import com.example.e_glicemia.util.Utilities
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_alarme.*
import kotlinx.android.synthetic.main.activity_glicemia.*

class AlarmeActivity : AppCompatActivity() {
    private var db: FirebaseFirestore? = null
    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarme)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        db = FirebaseFirestore.getInstance()


        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("alarmeId")

            Log.i("AlarmeActivity", "id = " + id)
            val docRef = db!!.collection("alarmes").document(id.toString())
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val alarme = documentSnapshot.toObject(AlarmeModel::class.java)
                if (alarme != null) {
                    Log.d("AlarmeActivity", "hora = " + alarme.hora)
                    editTextTimeAlarme.setText(alarme.hora)
                    switchAtivoAlarme.isChecked = alarme.ativo
                } else {
                    Log.d("AlarmeActivity", "alarme null")
                }
            }
        } else {
            Log.d("AlarmeActivity", "bundle null")
        }

        buttonSalvarAlarme.setOnClickListener {
            Log.d("AlarmeActivity", ">buttonSalvarAlarme!")
            if (editTextTimeAlarme.text.isNullOrEmpty()) {
                Toast.makeText(this@AlarmeActivity, "Favor preencher todos os campos!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val hora = editTextTimeAlarme.text.toString()
            val ativo = switchAtivoAlarme.isChecked

            if (Utilities.isValidTime(hora) == false) {
                Toast.makeText(this@AlarmeActivity, "Hora em formato inválido!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Log.d("AlarmeActivity", "id = " + id)
            if (id != null && id!!.isNotEmpty()) {
                Log.d("AlarmeActivity", "Updating alarme!")
                update(id!!, hora, ativo)
            } else {
                Log.d("AlarmeActivity", "Saving alarme!")
                adicionar(hora, ativo)
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun adicionar(hora: String, ativo: Boolean) {
        Log.d("AlarmeActivity", "Adicionar...")
        val currentUser = Firebase.auth.currentUser!!

        val alarme = AlarmeModel(
            userId = currentUser.uid,
            hora = hora,
            ativo = ativo
        )

        db!!.collection("alarmes")
            .add(alarme)
            .addOnSuccessListener {
                Toast.makeText(this@AlarmeActivity, "Inserido com sucesso ", Toast.LENGTH_LONG).show()
                //startActivity(Intent(this@GlicemiaActivity, MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this@AlarmeActivity, "Falha ao adicionar", Toast.LENGTH_SHORT).show()
            }
    }

    private fun update(id: String, hora: String, ativo: Boolean) {
        val currentUser = Firebase.auth.currentUser!!
        val alarme = AlarmeModel(
            userId = currentUser.uid,
            hora = hora,
            ativo = ativo
        )

        db!!.collection("alarmes")
            .document(id)
            .set(alarme)
            .addOnSuccessListener {
                Log.e("AlarmeActivity", "Alarme atualizado com sucesso!")
                Toast.makeText(applicationContext, "Alarme atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e("AlarmeActivity", "Erro ao atualizar alarme!", e)
                Toast.makeText(applicationContext, "Erro ao atualizar alarme!", Toast.LENGTH_SHORT).show()
            }
    }
}