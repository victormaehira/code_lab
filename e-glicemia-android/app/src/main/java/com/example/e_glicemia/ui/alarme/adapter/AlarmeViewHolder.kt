package com.example.e_glicemia.ui.alarme.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_glicemia.ui.alarme.AlarmeActivity
import com.example.e_glicemia.R
import com.google.firebase.firestore.FirebaseFirestore

class AlarmeViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    internal fun setHora(hora: String) {
        val textView = view.findViewById<TextView>(R.id.textViewListAlarmeHora)
        textView.text = hora
    }

    internal fun setAtivo(ativo: Boolean) {
        val switch = view.findViewById<Switch>(R.id.switchListAlarmeAtivo)
        switch.isChecked = ativo
    }

    internal fun setDeleteId(id: String) {
        val imageButton: ImageButton = view.findViewById<ImageButton>(R.id.imageButtonListAlarmeDelete)
        imageButton.setOnClickListener {
            deleteAlarme(id)
        }
    }

    internal fun setEditId(id: String) {
        val imageButton: ImageButton = view.findViewById<ImageButton>(R.id.imageButtonListAlarmeEdit)
        imageButton.setOnClickListener {
            updateAlarme(id)
        }
    }

    private fun deleteAlarme(id: String) {
        val db = FirebaseFirestore.getInstance()
        db!!.collection("alarmes")
            .document(id)
            .delete()
            .addOnCompleteListener {
                Toast.makeText(view.context, "Alarme excluido com sucesso!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateAlarme(id: String) {
        val intent = Intent(view.getContext(), AlarmeActivity::class.java)
        Log.d("AlarmeListActivity", "update id = " + id)
        intent.putExtra("alarmeId", id)
        view.getContext().startActivity(intent)
    }

}
