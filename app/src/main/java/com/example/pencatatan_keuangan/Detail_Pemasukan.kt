package com.example.pencatatan_keuangan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pencatatan_keuangan.Model.Pemasukan
import com.example.pencatatan_keuangan.databinding.ActivityDetailPemasukanBinding
import com.example.pencatatan_keuangan.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Detail_Pemasukan : AppCompatActivity() {

    private lateinit var note_content : EditText
    private lateinit var deskripsi : EditText
    private lateinit var input_uang : EditText
    private lateinit var confrim_btn : Button
    private lateinit var db_ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pemasukan)

        note_content = findViewById(R.id.note_content_text)
        deskripsi = findViewById(R.id.deskripsi)
        input_uang = findViewById(R.id.input_uang)
        confrim_btn = findViewById(R.id.confirm_btn)

        db_ref = FirebaseDatabase.getInstance().getReference("Pemasukan")

        confrim_btn.setOnClickListener {
            savePemasukan()
        }
    }
    private fun savePemasukan() {

        //getting value
        val noteContent = note_content.text.toString()
        val deskripsi_pemasukan = deskripsi.text.toString()
        val uang_masuk  = input_uang.text.toString()

        if (noteContent.isEmpty()) {
            note_content.error = "Please Insert note"
        } else if (deskripsi_pemasukan.isEmpty()) {
            deskripsi.error = "Please Insert deskripsi"
        } else if (uang_masuk.isEmpty()) {
            input_uang.error = "Please Insert uang "
        }

        val pemasukanId = db_ref.push().key!!

        val pemasukan = Pemasukan(pemasukanId, noteContent, deskripsi_pemasukan, uang_masuk)

        db_ref.child(pemasukanId).setValue(pemasukan)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Insert is Succesfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()

            }
    }
}