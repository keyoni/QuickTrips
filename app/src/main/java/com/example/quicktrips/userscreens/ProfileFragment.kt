package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentProfileBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        binding.btnTest.setOnClickListener() {
            val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
            val currentUserId = sharedPref.getInt("current_user_id", -1)
            mViewModel.getUsersById(currentUserId)

            mViewModel.mCurrentUser.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "Hello ${it[0].mFirstName}", Toast.LENGTH_SHORT).show()
            })

        }


    }

}