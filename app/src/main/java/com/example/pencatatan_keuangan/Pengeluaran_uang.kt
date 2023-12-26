package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Adapter.PengeluaranAdapter
import com.example.pencatatan_keuangan.Model.Pengeluaran
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Pengeluaran_uang : AppCompatActivity() {

    private lateinit var db_ref : DatabaseReference
    private lateinit var mUser : FirebaseUser
    private lateinit var mAuth: FirebaseAuth
    private lateinit var pengeluaranRecylerView : RecyclerView
    private lateinit var pengeluaranArrayList: ArrayList<Pengeluaran>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengeluaran_uang)

        pengeluaranRecylerView = findViewById(R.id.pengeluaranList)
        pengeluaranRecylerView.layoutManager = LinearLayoutManager(this)
        pengeluaranRecylerView.setHasFixedSize(true)

        val ImageButton = findViewById<ImageButton>(R.id.add_pengeluaran)
        ImageButton.setOnClickListener {
            val Intent = Intent (this, Detail_Pengeluaran::class.java)
            startActivity(Intent)
        }

        pengeluaranArrayList = arrayListOf()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        getPengeluaran()



    }

    private fun getPengeluaran() {
        val userId = mUser.uid
        db_ref = FirebaseDatabase.getInstance().getReference("Pengeluaran").child(userId)

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (pengeluaranSnapshot in snapshot.children){
                        val pengeluaran = pengeluaranSnapshot.getValue(Pengeluaran::class.java)
                        pengeluaranArrayList.add(pengeluaran!!)
                    }
                    pengeluaranRecylerView.adapter = PengeluaranAdapter(pengeluaranArrayList)
                } else {
                    Toast.makeText(this@Pengeluaran_uang, "Data Not Found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}