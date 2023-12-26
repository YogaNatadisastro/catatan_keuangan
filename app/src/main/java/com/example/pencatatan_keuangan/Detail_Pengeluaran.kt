package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pencatatan_keuangan.Model.Pengeluaran
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar

class Detail_Pengeluaran : AppCompatActivity() {

    private lateinit var note_content : EditText
    private lateinit var deskripsi : EditText
    private lateinit var input_uang : EditText
    private lateinit var tanggal : EditText
    private lateinit var confirm_btn : Button
    private lateinit var mUser: FirebaseUser
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userId : String
    private var max : Long = 0
    private var dataBaru : Int = 0
    private lateinit var db_ref : DatabaseReference
    val calandar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengeluaran)

        note_content = findViewById(R.id.note_content_text)
        deskripsi = findViewById(R.id.deskripsi)
        input_uang = findViewById(R.id.input_uang_pengeluaran)
        confirm_btn = findViewById(R.id.confirm_btn_pengeluaran)
        tanggal = findViewById(R.id.date_tanggal_pengeluaran)

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        userId = mUser.uid

        db_ref = FirebaseDatabase.getInstance().getReference("Pengeluaran").child(userId)
        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //menghitung ada berapa data dalam database
                if (snapshot.exists()) {
                    max = (snapshot.childrenCount)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        confirm_btn.setOnClickListener {
            savePengeluaran()
        }
    }

    private fun savePengeluaran() {
        //getting value
        val noteContent = note_content.text.toString()
        val deskripsi_pengeluaran = deskripsi.text.toString()
        val uang_keluar = input_uang.text.toString()


        if (noteContent.isEmpty()) {
            Toast.makeText(this, "Silahkan masukkan judul pemasukan", Toast.LENGTH_SHORT).show()
        } else if (deskripsi_pengeluaran.isEmpty()) {
            Toast.makeText(this, "Silahkan masukkan deskripsi pemasukan", Toast.LENGTH_SHORT).show()
        } else if (uang_keluar.isEmpty()) {
            Toast.makeText(this, "Silahkan masukkan uang pemasukan", Toast.LENGTH_SHORT).show()
        } else {
            val pengeluaran = Pengeluaran(userId, noteContent, deskripsi_pengeluaran, uang_keluar)

            val check = FirebaseDatabase.getInstance().getReference("Total Pengeluaran").child(userId)
            check.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val data = snapshot.child("total").value.toString().toInt()
                        dataBaru = data + uang_keluar.toInt()
                    } else {
                        check.child("total").setValue(uang_keluar).addOnCompleteListener { t ->
                            if (t.isSuccessful){
                                tambahData(pengeluaran)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            println(uang_keluar)
            if (dataBaru != 0){
                check.child("total").setValue(dataBaru).addOnCompleteListener { t->
                    if (t.isSuccessful){
                        tambahData(pengeluaran)
                    }
                }
            }
        }
    }
    private fun tambahData(pengeluaran: Pengeluaran) {
        val db_ref = FirebaseDatabase.getInstance().getReference("Pengeluaran").child(userId)
        db_ref.child((max + 1).toString()).setValue(pengeluaran).addOnCompleteListener { t ->
            if (t.isSuccessful) {
                Toast.makeText(this@Detail_Pengeluaran,"Data Insert is Succesfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Detail_Pengeluaran, Pengeluaran_uang::class.java))
            } else {
                Toast.makeText(this@Detail_Pengeluaran, "Error Data Input", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { err ->
            Toast.makeText(this@Detail_Pengeluaran, "Error ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }

}