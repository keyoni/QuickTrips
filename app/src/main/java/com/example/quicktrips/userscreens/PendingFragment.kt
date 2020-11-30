package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentPendingBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository
import com.example.quicktrips.userscreens.itemadapters.LocationItemAdapter
import com.example.quicktrips.userscreens.itemadapters.TripItemAdapter

class PendingFragment : Fragment(R.layout.fragment_pending) {

    private lateinit var mViewModel: AppViewModel

    private lateinit var binding: FragmentPendingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPendingBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this,factory).get(AppViewModel::class.java)

        // Gets UserId and isDoctor
        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        var currentUserId = sharedPref.getInt("current_user_id", -1)
        var currentUserStatus = sharedPref.getInt("current_user_isDoctor", -1)

        displayPendingTrips(currentUserId,currentUserStatus)

    }

    private fun displayPendingTrips(userId: Int, userStatus: Int){
        var adapter = TripItemAdapter(mViewModel, listOf(),userStatus)
        binding.rvTripPendingList.layoutManager = LinearLayoutManager(context)
        binding.rvTripPendingList.adapter = adapter

        if(userStatus == 1) {
            mViewModel.mAllTrips.observeForever(){
                adapter.mAllTrips = it
                adapter.notifyDataSetChanged()
            }
        } else {
            mViewModel.getUserPendingTrips(false,userId)
            mViewModel.mCurrentUserPendingTrips.observeForever(){
                adapter.mAllTrips = it
                adapter.notifyDataSetChanged()
            }
        }
    }


}