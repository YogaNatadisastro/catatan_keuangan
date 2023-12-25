package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Adapter.PengeluaranAdapter
import com.example.pencatatan_keuangan.Model.Pengeluaran
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Pengeluaran_uang : AppCompatActivity() {

    private lateinit var db_ref : DatabaseReference
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

        pengeluaranArrayList = arrayListOf<Pengeluaran>()
        getPengeluaran()

    }

    private fun getPengeluaran() {

        db_ref = FirebaseDatabase.getInstance().getReference("Pengeluaran")

        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (pengeluaranSnapshot in snapshot.children){
                        val pengeluaran = pengeluaranSnapshot.getValue(Pengeluaran::class.java)
                        pengeluaranArrayList.add(pengeluaran!!)
                    }
                    pengeluaranRecylerView.adapter = PengeluaranAdapter(pengeluaranArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}