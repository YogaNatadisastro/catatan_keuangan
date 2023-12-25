package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.pencatatan_keuangan.Model.Dompetku
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.reflect.Member

class Detail_Dompetku : AppCompatActivity() {

    private lateinit var confirm_btn : Button
    private lateinit var db_ref : DatabaseReference
    private lateinit var jumlah_uang : EditText
    private lateinit var radioGroupDompet : RadioGroup
    private lateinit var cash_btn: RadioButton
    private lateinit var saldo_btn: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dompetku)

        jumlah_uang = findViewById(R.id.jumlah_uang)
        radioGroupDompet = findViewById(R.id.jenis_dompet)
        cash_btn = findViewById(R.id.radio_cash)
        saldo_btn = findViewById(R.id.radio_saldo)

        db_ref = FirebaseDatabase.getInstance().getReference("Dompet-Ku")

        //add value dompetku
        confirm_btn.setOnClickListener {
            val selected_dompet: String = when {
                cash_btn.isChecked -> "cash"
                saldo_btn.isChecked -> "saldo"
                else -> "Pilih sala satu jenis dompet ya"
            }

            val dompetkuId = db_ref.push().key!!
            val input_jumlah_uang = jumlah_uang.text
            val dompetku = Dompetku(dompetkuId,)

        }


    }



    private fun saveDompetKu() {

    }
}