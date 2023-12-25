package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Setting_Activity : AppCompatActivity() {

    private lateinit var reset_btn : ImageButton
    private lateinit var logout_btn : ImageButton
    private lateinit var db_ref : FirebaseDatabase
    private lateinit var auth : FirebaseAuth
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        db_ref = FirebaseDatabase.getInstance()
        reset_btn = findViewById(R.id.reset_btn)
        logout_btn = findViewById(R.id.logout_btn)
        auth = FirebaseAuth.getInstance()

        logout_btn.setOnClickListener {
            logoutUser()
        }

        //fungsi button
        reset_btn.setOnClickListener {
            deleteAllData()
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_profile -> {
                    val Intent = Intent (this, Profile_Activity::class.java)
                    startActivity(Intent)
                    true
                }
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

    //method untuk
    fun logoutUser() {
        auth.signOut()
        Intent(this@Setting_Activity, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    fun deleteAllData() {
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference

        //remove all data
        reference.removeValue().addOnSuccessListener {
            Toast.makeText(this, "Data Berhasil di reset", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }
}