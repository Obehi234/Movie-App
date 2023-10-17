package com.example.streammoviesapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.streammoviesapplication.R
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

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.fragmentContainerView)
            return navController.navigateUp() || super.onSupportNavigateUp()
        }
    }




