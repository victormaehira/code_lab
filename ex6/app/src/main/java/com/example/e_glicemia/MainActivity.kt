package com.example.e_glicemia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_glicemia.model.Glicemia
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_glicemia_single.*
import kotlinx.android.synthetic.main.list_glicemia_single.view.*


class MainActivity : AppCompatActivity() {

    private var db: FirebaseFirestore? = null
    //private val mFirestoreList: RecyclerView? = null
    private var adapter: GlicemiaFirestoreRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = Firebase.auth
        val userName = auth.currentUser!!.displayName

        textViewMainName.text = "Olá, $userName"

        imageButtonMainAdcionaGlicemia.setOnClickListener{
            startActivity(Intent(this, GlicemiaActivity::class.java))
        }
        val mFirestoreList:RecyclerView = findViewById(R.id.recyclerViewMainActivitiy)

        db = FirebaseFirestore.getInstance()
        //mFirestoreList = findViewById(R.id.recyclerViewMainActivitiy)
        //query
        val query = db!!.collection("glicemias")
        //recyclerOption
        val options = FirestoreRecyclerOptions.Builder<Glicemia>().setQuery(query, Glicemia::class.java).build()

        adapter = GlicemiaFirestoreRecyclerAdapter(options)

        mFirestoreList.setHasFixedSize(false)
        mFirestoreList.layoutManager = LinearLayoutManager(this.applicationContext)
        mFirestoreList.adapter = adapter

    }
    private inner class GlicemiaViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view) {
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

    }
    private inner class GlicemiaFirestoreRecyclerAdapter internal constructor(options: FirestoreRecyclerOptions<Glicemia>) : FirestoreRecyclerAdapter<Glicemia, GlicemiaViewHolder>(options) {
        override fun onBindViewHolder(glicemiaViewHolder: GlicemiaViewHolder, position: Int, glicemia: Glicemia) {
            glicemiaViewHolder.setData(glicemia.data)
            glicemiaViewHolder.setHora(glicemia.hora)
            glicemiaViewHolder.setValor(glicemia.valor)
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

    private fun deleteGlicemia(id: String) {
        db!!.collection("glicemias")
            .document(id)
            .delete()
            .addOnCompleteListener {
                Toast.makeText(applicationContext, "Glicemia excluida com sucesso!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateGlicemia(id: String) {
        val intent = Intent(this, GlicemiaActivity::class.java)
        intent.putExtra("glicemiaId", id)
        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()

        if (adapter != null) {
            adapter!!.stopListening()
        }
    }
}


