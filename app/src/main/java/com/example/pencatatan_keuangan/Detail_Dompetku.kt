package com.example.pencatatan_keuangan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.pencatatan_keuangan.Model.Dompetku
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Detail_Dompetku : AppCompatActivity() {

    private lateinit var confirm_btn : Button
    private lateinit var db_ref : DatabaseReference
    private lateinit var jumlah_uang_dompet: EditText
    private lateinit var deskripsiDompetku: EditText
    private lateinit var mUser : FirebaseUser
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userId: String
    private var max : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dompetku)

        jumlah_uang_dompet= findViewById(R.id.jumlah_uang)
        deskripsiDompetku = findViewById(R.id.deskripsi_dompet)
        confirm_btn = findViewById(R.id.confirm_btn)

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        userId = mUser.uid

        db_ref = FirebaseDatabase.getInstance().getReference("Dompet-Ku").child(userId)
        db_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    max = (snapshot.childrenCount)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        //add value dompetku
        confirm_btn.setOnClickListener {
            saveDompetKu()
        }
    }

    private fun saveDompetKu() {
        //save value
        val deskripsiDompet = deskripsiDompetku.text.toString()
        val jumlah_uang = jumlah_uang_dompet.text.toString()

        if (deskripsiDompet.isEmpty()) {
            deskripsiDompetku.error = "Please Masukkan deskripsi dompet"
        } else if (jumlah_uang.isEmpty()) {
            jumlah_uang_dompet.error = "Please masukan jumlah uang"
        }
        val dompetku = Dompetku(userId, deskripsiDompet, jumlah_uang)

        db_ref.child((max + 1).toString()).setValue(dompetku)
            .addOnCompleteListener {
            Toast.makeText(this, "Data insert is Succesfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}