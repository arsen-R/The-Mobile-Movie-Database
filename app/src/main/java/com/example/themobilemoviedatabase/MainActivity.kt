package com.example.themobilemoviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.themobilemoviedatabase.databinding.ActivityMainBinding
import com.example.themobilemoviedatabase.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.home_screen,
            R.id.search_screen,
            R.id.favorite_screen,
            R.id.profile_screen
        ).build()

        navController = navHostFragment.navController

        with(binding) {
            toolbar.setupWithNavController(navController, appBarConfiguration)
            bottomNavBar.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.search_screen) {
                    appBarLayout.isVisible = false
                    bottomNavBar.isVisible = true
                } else if (destination.id == R.id.home_screen) {
                    appBarLayout.isVisible = true
                    bottomNavBar.isVisible = true
                } else if (destination.id == R.id.favorite_screen) {
                    appBarLayout.isVisible = true
                    bottomNavBar.isVisible = true
                } else if (destination.id == R.id.profile_screen) {
                    appBarLayout.isVisible = true
                    bottomNavBar.isVisible = true
                } else {
                    appBarLayout.isVisible = true
                    bottomNavBar.isVisible = false
                }
            }
        }
    }
}