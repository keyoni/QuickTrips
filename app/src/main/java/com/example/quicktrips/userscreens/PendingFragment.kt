package com.example.quicktrips.userscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentPendingBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding

class PendingFragment : Fragment(R.layout.fragment_pending) {


    private lateinit var binding: FragmentPendingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPendingBinding.bind(view)

    }


}