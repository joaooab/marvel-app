package com.example.marvelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController(binding)
        installSplashScreen()
    }

    private fun setupNavController(binding: ActivityMainBinding) {
        val navController = (supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment).navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestinations = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestinations) {
                binding.toolbarApp.setNavigationIcon(R.drawable.arrow_back)
            }
        }
    }
}