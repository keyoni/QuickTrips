package com.example.quicktrips

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quicktrips.databinding.FragmentSignInBinding
import com.example.quicktrips.userscreens.UserActivity


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)


        binding.btnSignIn.setOnClickListener() {
            val intent = Intent(activity,UserActivity::class.java).also {
                startActivity(it)
            }
        }
    }


}