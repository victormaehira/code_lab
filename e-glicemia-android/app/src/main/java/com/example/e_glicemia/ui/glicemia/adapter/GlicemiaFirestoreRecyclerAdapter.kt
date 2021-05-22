package com.example.e_glicemia.ui.glicemia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.e_glicemia.R
import com.example.e_glicemia.data.glicemia.model.GlicemiaModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class GlicemiaFirestoreRecyclerAdapter(options: FirestoreRecyclerOptions<GlicemiaModel>) : FirestoreRecyclerAdapter<GlicemiaModel, GlicemiaViewHolder>(options) {
    override fun onBindViewHolder(glicemiaViewHolder: GlicemiaViewHolder, position: Int, glicemiaModel: GlicemiaModel) {
        glicemiaViewHolder.setData(glicemiaModel.data)
        glicemiaViewHolder.setHora(glicemiaModel.hora)
        glicemiaViewHolder.setValor(glicemiaModel.valor)
        val documentId = snapshots.getSnapshot(position).id
        glicemiaViewHolder.setDeleteId(documentId)
        glicemiaViewHolder.setEditId(documentId)

        Log.d("TESTE"," documentId = " + documentId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlicemiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_glicemia_single, parent, false)

        return GlicemiaViewHolder(view)
    }
}