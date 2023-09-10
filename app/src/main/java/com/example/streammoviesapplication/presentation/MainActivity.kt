package com.example.streammoviesapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streammoviesapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.itemIconTintList = null
    }




}