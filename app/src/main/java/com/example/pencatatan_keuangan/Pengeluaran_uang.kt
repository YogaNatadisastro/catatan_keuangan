package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Pengeluaran_uang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengeluaran_uang)

        val ImageButton = findViewById<ImageButton>(R.id.add_pengeluaran)
        ImageButton.setOnClickListener {
            val Intent = Intent (this, Detail_Pengeluaran::class.java)
            startActivity(Intent)
        }
    }
}