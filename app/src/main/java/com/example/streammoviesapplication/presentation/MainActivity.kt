package com.example.streammoviesapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.databinding.ActivityMainBinding
import com.example.streammoviesapplication.presentation.navFragments.HomeFragment
import com.example.streammoviesapplication.presentation.navFragments.ProfileFragment
import com.example.streammoviesapplication.presentation.navFragments.SearchMoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.play -> {
                    loadFragment(SearchMoviesFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    Log.d("TAG", "FRAGMENT ERROR")
                    false
                }
            }
        }


    }

    private fun loadFragment(fragment: Fragment): Boolean {
        // Load the specified fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()

        // Return true when the fragment is successfully loaded
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.fragmentContainerView)
            return navController.navigateUp() || super.onSupportNavigateUp()
        }


    }




