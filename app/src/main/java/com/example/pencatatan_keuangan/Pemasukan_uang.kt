package com.example.pencatatan_keuangan

import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Pemasukan_uang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemasukan_uang)

        val ImageButton = findViewById<ImageButton>(R.id.add_pemasukan)
        ImageButton.setOnClickListener {
            val Intent = Intent (this, Detail_Pemasukan::class.java)
            startActivity(Intent)
        }

        fun setupRecyclerView() {

        }
    }
}