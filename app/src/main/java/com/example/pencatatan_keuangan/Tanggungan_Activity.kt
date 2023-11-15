package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Tanggungan_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanggungan)

        val ImageButton = findViewById<ImageButton>(R.id.add_tanggungan)
        ImageButton.setOnClickListener {
            val Intent = Intent (this, Detail_Tanggungan_Activity::class.java)
            startActivity(Intent)
        }

    }
}