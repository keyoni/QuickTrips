package com.example.quicktrips.userscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationsBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this,factory).get(AppViewModel::class.java)
        displayLocations()
        binding.btnAddLocation.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.navigate_to_add_location)
        }

    }

    private fun displayLocations (){

        var adapter = LocationItemAdapter(mViewModel, listOf())
        binding.rvLocationList.layoutManager = LinearLayoutManager(context)
        binding.rvLocationList.adapter = adapter

        mViewModel.mAllLocations.observeForever(Observer {
            adapter.mAllLocations = it
            adapter.notifyDataSetChanged()

        })
    }

}