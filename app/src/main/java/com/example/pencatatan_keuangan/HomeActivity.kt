package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.pencatatan_keuangan.Model.Pemasukan
import com.example.pencatatan_keuangan.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var text_totalPemasukan: TextView
    private lateinit var text_totalPengeluaran: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testGetData()
        testGetDataPengeluaran()

        val imageButton1 = findViewById<ImageButton>(R.id.pemasukan_btn)
        imageButton1.setOnClickListener {
            val Intent = Intent (this, Pemasukan_uang::class.java)
            startActivity(Intent)
        }

        val imageButton2 = findViewById<ImageButton>(R.id.pengeluaran_btn)
        imageButton2.setOnClickListener {
            val Intent = Intent (this, Pengeluaran_uang::class.java)
            startActivity(Intent)
        }

        val imageButton3 = findViewById<ImageButton>(R.id.dompetku_btn)
        imageButton3.setOnClickListener {
            val Intent = Intent ( this, Dompetku_Activity::class.java)
            startActivity(Intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_profile -> {
                    val Intent = Intent (this, Profile_Activity::class.java)
                    startActivity(Intent)
                    true
                }
                R.id.bottom_setting -> {
                    val Intent = Intent (this, Setting_Activity::class.java)
                    startActivity(Intent)
                    true
                }
                R.id.bottom_setting -> {
                    val Intent = Intent (this, Setting_Activity::class.java)
                    startActivity(Intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun testGetData() {
        text_totalPemasukan = findViewById(R.id.total_pemasukan)
        databaseReference = FirebaseDatabase.getInstance().getReference("Pemasukan")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data : String = snapshot.child("input_uang").getValue().toString()
                text_totalPemasukan.setText(data)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun testGetDataPengeluaran() {
        text_totalPengeluaran = findViewById(R.id.total_pengeluaran)
        databaseReference = FirebaseDatabase.getInstance().getReference("Pengluaran")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data : String = snapshot.getValue().toString()
                text_totalPengeluaran.setText(data)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getDataPengeluaran(input_uang: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        databaseReference.child(input_uang).get().addOnSuccessListener {
            if (it.exists()){
                val uangPengeluaran = it.child("input_uang").value
                binding.totalPengeluaran.text = uangPengeluaran.toString()
            }
        }
    }
    private fun getDataPemasukan(input_uang: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Pemasukan")
        databaseReference.child(input_uang).get().addOnSuccessListener {
            if (it.exists()){
                val uangPemasukan = it.child("input_uang").value
                binding.totalPemasukan.text = uangPemasukan.toString()
            }
        }
    }

}