package com.example.quicktrips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.quicktrips.databinding.ActivityMainBinding
import com.example.quicktrips.databinding.ActivitySigninBinding
import com.example.quicktrips.databinding.ActivitySigninBinding.inflate
import com.example.quicktrips.databinding.ActivityUserBinding.inflate
import com.example.quicktrips.db.entites.User


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: SignInActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivity.inflate(layoutInflater)
        setContentView(binding.root)



        }

    }
