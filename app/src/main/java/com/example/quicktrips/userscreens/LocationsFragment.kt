package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentLocationsBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository
import com.example.quicktrips.userscreens.itemadapters.LocationItemAdapter

class LocationsFragment : Fragment(R.layout.fragment_locations) {

    private lateinit var mViewModel: AppViewModel


    private lateinit var binding: FragmentLocationsBinding
    private  var numOfLocations = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationsBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)

        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        var currentUserId = sharedPref.getInt("current_user_id", -1)
        var currentUserStatus = sharedPref.getInt("current_user_isDoctor", -1)

        mViewModel = ViewModelProvider(this,factory).get(AppViewModel::class.java)

        if(currentUserStatus == 1){
            binding.btnAddLocation.visibility = View.VISIBLE
        } else {
            binding.btnAddLocation.visibility = View.INVISIBLE
        }
       displayLocations(currentUserId,currentUserStatus)
        // todo : USE CASE: Location ADD
        binding.btnAddLocation.setOnClickListener() {
            Log.d("LOCFRAG","Num of loc: $numOfLocations")
            if (numOfLocations >= 6) {
                Toast.makeText(
                    context,
                    "Too many locations! Don't overwhelm them, Doctor",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val bundle = bundleOf("AddButton" to true)
                Navigation.findNavController(it).navigate(R.id.navigate_to_add_location, bundle)
            }
        }

    }

    private fun displayLocations (userId: Int, userStatus: Int){

        var adapter = LocationItemAdapter(mViewModel, listOf(),userId,userStatus,viewLifecycleOwner)
        binding.rvLocationList.layoutManager = LinearLayoutManager(context)
        binding.rvLocationList.adapter = adapter

        mViewModel.mAllLocations.observeForever(Observer {
            numOfLocations = it.size
            adapter.mAllLocations = it
            adapter.notifyDataSetChanged()


        })

    }

    override fun onDestroy() {
        super.onDestroy()
        numOfLocations = 0

    }
}