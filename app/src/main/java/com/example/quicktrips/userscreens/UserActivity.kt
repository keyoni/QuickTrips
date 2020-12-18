package com.example.quicktrips.userscreens

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.quicktrips.R
import com.example.quicktrips.databinding.ActivityMainBinding
import com.example.quicktrips.databinding.ActivityUserBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var mViewModel: AppViewModel
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase(this)
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this,factory).get(AppViewModel::class.java)

        val sharedPref = this.getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        var currentUserId = sharedPref.getInt("current_user_id", -1)
        var currentUserStatus = sharedPref.getInt("current_user_isDoctor", -1)

        val navController = findNavController(R.id.nhUsers)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.pendingFragment,R.id.locationsFragment, R.id.profileFragment,R.id.settingsFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.pendingFragment -> {
                    Log.d("NAVTEST", "Pending Clicked")
                    //todo: add check for pending trips, if non pop toast and gave to locations
                    //might need a small class to check for pending trips or just add a view model mess here lol
                    //example
                    // R.id.home -> {
                    //                    findNavController(R.id.nav_host_fragment)
                    //                        .navigate(R.id.mainFragment)
                    //                }
                    //var checkPending = false
                    mViewModel.getUserPendingTrips(false, currentUserId)
                    mViewModel.mCurrentUserPendingTrips.observe(this, Observer  {
                        if (it.isNotEmpty()) {
                        navController.navigate(R.id.pendingFragment)
                    }   else {
                        Toast.makeText(this, "No Pending Trips, Pick a location!", Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.locationsFragment)
                    }
                    })

                    true
                }
                R.id.locationsFragment ->{
                    navController.navigate(R.id.locationsFragment)
                    true
                }
                R.id.profileFragment ->{
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.settingsFragment ->{
                    navController.navigate(R.id.settingsFragment)
                    true
                }

                else -> true


        }
    }
}
}