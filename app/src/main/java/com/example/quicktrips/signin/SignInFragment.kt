package com.example.quicktrips.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentSignInBinding


class SignInFragment : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

    }


}