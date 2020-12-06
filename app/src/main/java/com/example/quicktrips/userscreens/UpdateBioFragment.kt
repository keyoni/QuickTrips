package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentUpdateBioBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository

class UpdateBioFragment(

) : Fragment(R.layout.fragment_update_bio) {

    private lateinit var binding: FragmentUpdateBioBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBioBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        val currentUserId = sharedPref.getInt("current_user_id", -1)

        //current users current bio to populate et

        // save button saves new bio
        binding.btnSave.setOnClickListener(){
            val newBio = binding.etUpdate.text.toString()

            //TODO: USE CASE "USER TRIES TO ENTER EMPTY BIO"
            if (newBio == ""){
                Toast.makeText(context, "Please share more about yourself!", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.updateBio(newBio,currentUserId)
                Navigation.findNavController(it).navigate(R.id.navigate_to_profileFragment)
            }
        }


        binding.btnCancel.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.navigate_to_profileFragment)
        }

    }

}

