package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Adapter.DompetkuAdapter
import com.example.pencatatan_keuangan.Model.Dompetku
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Dompetku_Activity : AppCompatActivity() {

    private lateinit var db_ref : DatabaseReference
    private lateinit var mUser: FirebaseUser
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dompetRecyclerView : RecyclerView
    private lateinit var dompetkuArrayList : ArrayList<Dompetku>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dompetku)

        dompetRecyclerView = findViewById(R.id.dompetku_recycler)
        dompetRecyclerView.layoutManager = LinearLayoutManager(this)
        dompetRecyclerView.setHasFixedSize(true)

        val Button_to_detailDompet = findViewById<Button>(R.id.input_dompetku)
        Button_to_detailDompet.setOnClickListener {
            val Intent = Intent (this, Detail_Dompetku::class.java)
            startActivity(Intent)
        }
        dompetkuArrayList = arrayListOf()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        getDompetku()
    }

    private fun getDompetku() {
        val userId = mUser.uid
        db_ref = FirebaseDatabase.getInstance().getReference("Dompet-ku").child(userId)

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dompetkuSnapshot in snapshot.children){
                        val dompetku = dompetkuSnapshot.getValue(Dompetku::class.java)
                        dompetkuArrayList.add(dompetku!!)
                    }
                    dompetRecyclerView.adapter = DompetkuAdapter(dompetkuArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}