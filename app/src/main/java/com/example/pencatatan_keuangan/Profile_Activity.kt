
package com.example.pencatatan_keuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Profile_Activity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        getUserData()

        //bottomnavbar
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

    private fun getUserData() {
        val userId = "userId"
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val getUserData: DatabaseReference = database.getReference("User")

        getUserData.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val name = snapshot.child("nama").getValue(String::class.java)
                    val emial = snapshot.child("email").getValue(String::class.java)
                    println("Name: $name, Age: $emial")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}