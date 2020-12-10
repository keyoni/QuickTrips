package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentViewAllUsersBinding
import com.example.quicktrips.databinding.FragmentViewUserProfileBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.entites.Trip
import com.example.quicktrips.db.repos.AppRepository
import com.example.quicktrips.userscreens.itemadapters.TripItemAdapter
import com.example.quicktrips.userscreens.itemadapters.TripTravelledItemAdapter


class ViewUserProfileFragment : Fragment(R.layout.fragment_view_user_profile) {
    private lateinit var binding: FragmentViewUserProfileBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewUserProfileBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        val currentUserId = sharedPref.getInt("current_user_id", -1)
        val currentUserStatus = sharedPref.getInt("current_user_isDoctor", -1)

        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)


        //Log.d("UserProfiles","UserId: $name")
        displayProfile(arguments?.getInt("UserIdProfile"))

    }

    fun displayProfile(userId: Int?) {
        mViewModel.getUsersById(userId!!)

        mViewModel.mCurrentUser.observe(viewLifecycleOwner, Observer {
            binding.tvFirstNameAdmin.text = it[0].mFirstName
            binding.tvLastNameAdmin.text = it[0].mLastName
            binding.tvBioAdmin.text = it[0].mBio
        })

        //Make new layout for travelld and untravvled in one list and just get trip by user
            var adapter = TripTravelledItemAdapter(mViewModel, listOf(),0, true)
            binding.rvTripsAdmin.layoutManager = LinearLayoutManager(context)
            binding.rvTripsAdmin.adapter = adapter

            mViewModel.getAllUserTrips(userId)
            mViewModel.mCurrentUserAllTrips.observe(viewLifecycleOwner, Observer{
                adapter.mAllTrips = it
                adapter.notifyDataSetChanged()
            })


    }



}

