package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Dompetku_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dompetku)

        val Button_to_detailDompet = findViewById<Button>(R.id.input_dompetku)
        Button_to_detailDompet.setOnClickListener {
            val Intent = Intent (this, Detail_Dompetku::class.java)
            startActivity(Intent)

        }
    }
}