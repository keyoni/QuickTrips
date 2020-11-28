package com.example.quicktrips.userscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentProfileBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

    }


}