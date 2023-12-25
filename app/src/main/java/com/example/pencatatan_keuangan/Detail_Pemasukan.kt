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


class Detail_Pemasukan : AppCompatActivity() {


    private lateinit var note_content : EditText
    private lateinit var deskripsi : EditText
    private lateinit var input_uang : EditText
    private lateinit var confrim_btn : Button
    private lateinit var mUser : FirebaseUser
    private lateinit var mAuth : FirebaseAuth
    private var max : Long = 0
    private lateinit var userId : String


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
        println("id $userId")

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
                Toast.makeText(this, "Error Broh... 1", Toast.LENGTH_SHORT).show()
            } else if (deskripsi_pemasukan.isEmpty()) {
                Toast.makeText(this, "Error Broh... 2", Toast.LENGTH_SHORT).show()
            } else if (uang_masuk.isEmpty()) {
                Toast.makeText(this, "Error Broh... 3", Toast.LENGTH_SHORT).show()
            } else {
                val pemasukan = Pemasukan(userId, noteContent, deskripsi_pemasukan, uang_masuk)

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
    }
}