package com.example.e_glicemia.ui.alarme.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.e_glicemia.R
import com.example.e_glicemia.data.alarme.model.AlarmeModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class AlarmeFirestoreRecyclerAdapter(options: FirestoreRecyclerOptions<AlarmeModel>): FirestoreRecyclerAdapter<AlarmeModel, AlarmeViewHolder>(options) {
    override fun onBindViewHolder(alarmeViewHolder: AlarmeViewHolder, position: Int, alarmeModel: AlarmeModel) {
        alarmeViewHolder.setHora(alarmeModel.hora)
        alarmeViewHolder.setAtivo(alarmeModel.ativo)
        val documentId = snapshots.getSnapshot(position).id
        alarmeViewHolder.setDeleteId(documentId)
        alarmeViewHolder.setEditId(documentId)

        Log.d("TESTE"," documentId = " + documentId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_alarme_single, parent, false)

        return AlarmeViewHolder(view)
    }
}