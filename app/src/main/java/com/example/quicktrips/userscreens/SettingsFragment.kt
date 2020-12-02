package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentSettingsBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {


    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.btnLogOut.setOnClickListener(){

            var userId = editor.putInt("current_user_id", -1).commit()
            var userisDoctor = editor.putInt("current_user_isDoctor",-1).commit()

            requireActivity().finish()
        }

    }


}