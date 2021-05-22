package com.example.e_glicemia.ui.glicemia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_glicemia.R
import com.example.e_glicemia.ui.glicemia.adapter.GlicemiaFirestoreRecyclerAdapter
import com.example.e_glicemia.data.glicemia.model.GlicemiaModel
import com.example.e_glicemia.ui.alarme.AlarmeListActivity
import com.example.e_glicemia.ui.signUp.SignUpActivity
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db: FirebaseFirestore? = null
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

        imageButtonListAlarmes.setOnClickListener{
            startActivity(Intent(this, AlarmeListActivity::class.java))
        }

        imageButtonHome.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        
        val mFirestoreList:RecyclerView = findViewById(R.id.recyclerViewMainActivitiy)

        db = FirebaseFirestore.getInstance()
        val query = db!!.collection("glicemias").whereEqualTo("userId", auth.currentUser!!.uid)

        val options = FirestoreRecyclerOptions.Builder<GlicemiaModel>().setQuery(query, GlicemiaModel::class.java).build()

        adapter =
            GlicemiaFirestoreRecyclerAdapter(
                options
            )

        mFirestoreList.setHasFixedSize(false)
        mFirestoreList.layoutManager = LinearLayoutManager(this.applicationContext)
        mFirestoreList.adapter = adapter

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


