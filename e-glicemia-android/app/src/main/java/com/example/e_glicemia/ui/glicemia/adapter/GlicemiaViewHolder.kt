package com.example.e_glicemia.ui.glicemia.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_glicemia.ui.glicemia.GlicemiaActivity
import com.example.e_glicemia.R
import com.google.firebase.firestore.FirebaseFirestore

class GlicemiaViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    internal fun setData(data: String) {
        val textView = view.findViewById<TextView>(R.id.textViewListGlicemiaDia)
        textView.text = data
    }
    internal fun setHora(hora: String) {
        val textView = view.findViewById<TextView>(R.id.textViewListGlicemiaHora)
        textView.text = hora
    }
    internal fun setValor(valor: Int) {
        val textView = view.findViewById<TextView>(R.id.textViewListGlicemiaValor)
        textView.text = valor.toString()
    }

    internal fun setDeleteId(id: String) {
        val imageButton: ImageButton = view.findViewById<ImageButton>(R.id.imageButtonListGlicemiaDelete)
        imageButton.setOnClickListener {
            deleteGlicemia(id)
        }
    }

    internal fun setEditId(id: String) {
        val imageButton: ImageButton = view.findViewById<ImageButton>(R.id.imageButtonListGlicemiaEdit)
        imageButton.setOnClickListener {
            updateGlicemia(id)
        }
    }

    private fun deleteGlicemia(id: String) {
        val db = FirebaseFirestore.getInstance()
        db!!.collection("glicemias")
            .document(id)
            .delete()
            .addOnCompleteListener {
                Toast.makeText(view.getContext(), "Glicemia excluida com sucesso!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateGlicemia(id: String) {
        val intent = Intent(view.getContext(), GlicemiaActivity::class.java)
        Log.d("MainActivity", "update id = " + id)
        intent.putExtra("glicemiaId", id)
        view.getContext().startActivity(intent)
    }


}
