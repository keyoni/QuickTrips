package com.example.quicktrips

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.quicktrips.databinding.ActivityMainBinding
import com.example.quicktrips.db.entites.User
/**
Quick Trips Application by Keyoni McNair

 All References to Doctor Who to their respective owners

*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }

    }
