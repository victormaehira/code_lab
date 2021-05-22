package com.example.e_glicemia.ui.alarme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_glicemia.R
import com.example.e_glicemia.ui.alarme.adapter.AlarmeFirestoreRecyclerAdapter
import com.example.e_glicemia.data.alarme.model.AlarmeModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_alarme_list.*


class AlarmeListActivity : AppCompatActivity() {
    private var db: FirebaseFirestore? = null
    private var adapter: AlarmeFirestoreRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarme_list)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val auth = Firebase.auth

        imageButtonAdicionarAlarme.setOnClickListener{
            startActivity(Intent(this, AlarmeActivity::class.java))
        }

        val mFirestoreList:RecyclerView = findViewById(R.id.recyclerViewAlarmes)

        db = FirebaseFirestore.getInstance()
        val query = db!!.collection("alarmes").whereEqualTo("userId", auth.currentUser!!.uid)

        val options = FirestoreRecyclerOptions.Builder<AlarmeModel>().setQuery(query, AlarmeModel::class.java).build()

        Log.i("AlarmeListActivity", "Teste do Adapater")
        adapter =
            AlarmeFirestoreRecyclerAdapter(
                options
            )

        mFirestoreList.setHasFixedSize(false)
        mFirestoreList.layoutManager = LinearLayoutManager(this.applicationContext)
        mFirestoreList.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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