package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val imageButton1 = findViewById<ImageButton>(R.id.pemasukan_btn)
        imageButton1.setOnClickListener {
            val Intent = Intent (this, Pemasukan_uang::class.java)
            startActivity(Intent)
        }

        val imageButton2 = findViewById<ImageButton>(R.id.pengeluaran_btn)
        imageButton2.setOnClickListener {
            val Intent = Intent (this, Pengeluaran_uang::class.java)
            startActivity(Intent)
        }

        val imageButton3 = findViewById<ImageButton>(R.id.hutang_btn)
        imageButton3.setOnClickListener {
            val Intent = Intent (this, Tanggungan_Activity::class.java)
            startActivity(Intent)
        }

        val imageButton4 = findViewById<ImageButton>(R.id.dompetku_btn)
        imageButton4.setOnClickListener {
            val Intent = Intent ( this, Dompetku_Activity::class.java)
            startActivity(Intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_profile -> {
                    val Intent = Intent (this, Profile_Activity::class.java)
                    startActivity(Intent)
                    true
                }
                R.id.bottom_setting -> {
                    val Intent = Intent (this, Setting_Activity::class.java)
                    startActivity(Intent)
                    true
                }
                else -> false
            }
        }
    }
}