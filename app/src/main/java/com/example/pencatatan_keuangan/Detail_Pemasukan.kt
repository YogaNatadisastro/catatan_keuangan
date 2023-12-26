package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pencatatan_keuangan.Model.Pemasukan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.sql.Date


class Detail_Pemasukan : AppCompatActivity() {


    private lateinit var note_content : EditText
    private lateinit var deskripsi : EditText
    private lateinit var input_uang : EditText
    private lateinit var dateTime : Date
    private lateinit var confrim_btn : Button
    private lateinit var mUser : FirebaseUser
    private lateinit var mAuth : FirebaseAuth
    private var max : Long = 0
    private lateinit var userId : String
    private var dataBaru: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemasukan)

        note_content = findViewById(R.id.note_content_text)
        deskripsi = findViewById(R.id.deskripsi)
        input_uang = findViewById(R.id.input_uang)
        confrim_btn = findViewById(R.id.confirm_btn_pemasukan)

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        userId = mUser.uid

        val ref = FirebaseDatabase.getInstance().getReference("Pemasukan").child(userId)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    max = (snapshot.childrenCount)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        confrim_btn.setOnClickListener {

            val noteContent = note_content.text.toString()
            val deskripsi_pemasukan = deskripsi.text.toString()
            val uang_masuk  = input_uang.text.toString()

            if (noteContent.isEmpty()) {
                Toast.makeText(this, "Silahkan masukan judul pemasukan", Toast.LENGTH_SHORT).show()
            } else if (deskripsi_pemasukan.isEmpty()) {
                Toast.makeText(this, "Silahkan masukan deskripsi ", Toast.LENGTH_SHORT).show()
            } else if (uang_masuk.isEmpty()) {
                Toast.makeText(this, "Masukan uang pemasukan", Toast.LENGTH_SHORT).show()
            } else {
                val pemasukan = Pemasukan(userId, noteContent, deskripsi_pemasukan, uang_masuk)

                val cek = FirebaseDatabase.getInstance().getReference("Total Pemasukan").child(userId)
                cek.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            val data = snapshot.child("total").value.toString().toInt()
                            dataBaru = data + uang_masuk.toInt()
                        } else {
                            cek.child("total").setValue(uang_masuk).addOnCompleteListener{ t ->
                                if (t.isSuccessful){
                                    tambahData(pemasukan)
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

                println(uang_masuk)
                if (dataBaru != 0){
                    cek.child("total").setValue(dataBaru).addOnCompleteListener { t->
                        if (t.isSuccessful){
                            tambahData(pemasukan)
                        }
                    }
                }
            }
        }
    }

    private fun tambahData(pemasukan: Pemasukan) {
        val dbref = FirebaseDatabase.getInstance().getReference("Pemasukan").child(userId)
        dbref.child((max + 1).toString()).setValue(pemasukan).addOnCompleteListener { t ->
            if (t.isSuccessful){
                Toast.makeText(this@Detail_Pemasukan, "Data Insert is Succesfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Detail_Pemasukan, Pemasukan_uang::class.java))
            } else {
                Toast.makeText(this@Detail_Pemasukan, "Error Broh...", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { err ->
            Toast.makeText(this@Detail_Pemasukan, "Error ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }
}