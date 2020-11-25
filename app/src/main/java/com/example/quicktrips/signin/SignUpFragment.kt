package com.example.quicktrips.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.UserDao


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding

    var mUserDao : UserDao? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.btnSignUp.setOnClickListener(){

        }
    }


}