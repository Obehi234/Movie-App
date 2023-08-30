package com.example.streammoviesapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streammoviesapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.itemIconTintList = null
    }
}