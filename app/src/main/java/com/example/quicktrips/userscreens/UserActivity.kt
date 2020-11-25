package com.example.quicktrips.userscreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.quicktrips.R
import com.example.quicktrips.databinding.ActivityMainBinding
import com.example.quicktrips.databinding.ActivityUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nhUsers)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.pendingFragment,R.id.locationsFragment, R.id.profileFragment,R.id.settingsFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}