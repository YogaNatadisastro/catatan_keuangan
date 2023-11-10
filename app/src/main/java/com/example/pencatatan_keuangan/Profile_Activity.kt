
package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile_Activity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_home -> {
                    val Intent = Intent (this, HomeActivity::class.java)
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