package com.example.pencatatan_keuangan

import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pencatatan_keuangan.Adapter.PemasukanAdapter
import com.example.pencatatan_keuangan.Model.Pemasukan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue


class Pemasukan_uang : AppCompatActivity() {

    private lateinit var db_ref : DatabaseReference
    private lateinit var pemasukanRecyclerView: RecyclerView
    private lateinit var pemasukanArrayList: ArrayList<Pemasukan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemasukan_uang)

        pemasukanRecyclerView = findViewById(R.id.pemasukanList)
        pemasukanRecyclerView.layoutManager = LinearLayoutManager(this)
        pemasukanRecyclerView.setHasFixedSize(true)

        val ImageButton = findViewById<ImageButton>(R.id.add_pemasukan)
        ImageButton.setOnClickListener {
            val Intent = Intent (this, Detail_Pemasukan::class.java)
            startActivity(Intent)
        }

        pemasukanArrayList = arrayListOf<Pemasukan>()
        getPemasukanData()

    }

    private fun getPemasukanData() {

        db_ref = FirebaseDatabase.getInstance().getReference("Pemasukan")

        db_ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (pemasukanSnapshot in snapshot.children){
                        val pemasukan = pemasukanSnapshot.getValue(Pemasukan::class.java)
                        pemasukanArrayList.add(pemasukan!!)
                    }
                    pemasukanRecyclerView.adapter = PemasukanAdapter(pemasukanArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}