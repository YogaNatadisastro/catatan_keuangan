package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pencatatan_keuangan.Model.Pengeluaran
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Detail_Pengeluaran : AppCompatActivity() {

    private lateinit var note_content : EditText
    private lateinit var deskripsi : EditText
    private lateinit var input_uang : EditText
    private lateinit var confirm_btn : Button
    private lateinit var db_ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengeluaran)

        note_content = findViewById(R.id.note_content_text)
        deskripsi = findViewById(R.id.deskripsi)
        input_uang = findViewById(R.id.input_uang_pengeluaran)
        confirm_btn = findViewById(R.id.confirm_btn_pengeluaran)

        db_ref = FirebaseDatabase.getInstance().getReference("Pengeluaran")

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
            note_content.error = "Please insert note"
        } else if (deskripsi_pengeluaran.isEmpty()) {
            deskripsi.error = "Please insert deskripsi"
        } else if (uang_keluar.isEmpty()) {
            input_uang.error = "Please insert pengeluaran"

        }

        val pengeluaranId = db_ref.push().key!!

        val pengeluaran = Pengeluaran(pengeluaranId, noteContent, deskripsi_pengeluaran, uang_keluar)

        val intent = Intent(this, Pengeluaran_uang::class.java)
        startActivity(intent)

        db_ref.child(pengeluaranId).setValue(pengeluaran)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Insert is Succesful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}