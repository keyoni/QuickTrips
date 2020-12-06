package com.example.quicktrips.userscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentProfileBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository
import com.example.quicktrips.userscreens.itemadapters.TripItemAdapter
import com.example.quicktrips.userscreens.itemadapters.TripTravelledItemAdapter

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        val currentUserId = sharedPref.getInt("current_user_id", -1)
        val currentUserStatus = sharedPref.getInt("current_user_isDoctor",-1)

        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        displayProfile(currentUserId,currentUserStatus)

        binding.btnEdit.setOnClickListener() {
            Navigation.findNavController(it).navigate(R.id.navigate_to_updateBioFragment)
            mViewModel.getUsersById(currentUserId)


            mViewModel.mCurrentUser.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "Hello ${it[0].mFirstName}", Toast.LENGTH_SHORT).show()
            })

        }


    }

    private fun displayProfile(userId: Int, userStatus: Int) {

        // For non-admin (Doctor) users
       if (userStatus == 0) {
            mViewModel.getUsersById(userId)
           binding.btnEdit.visibility = View.VISIBLE
           binding.btnNext.visibility = View.INVISIBLE
           binding.btnPrev.visibility = View.INVISIBLE

            mViewModel.mCurrentUser.observe(viewLifecycleOwner, Observer {
               binding.tvFirstName.text = it[0].mFirstName
               binding.tvLastName.text = it[0].mLastName
               binding.tvBio.text = it[0].mBio
            })

            var adapter = TripTravelledItemAdapter(mViewModel, listOf(),userStatus)
            binding.rvTravelledTrips.layoutManager = LinearLayoutManager(context)
            binding.rvTravelledTrips.adapter = adapter

            mViewModel.getUserTravelledTrips(true,userId)
            mViewModel.mCurrentUserTravelledTrips.observeForever(){
                adapter.mAllTrips = it
                adapter.notifyDataSetChanged()
                }
            }
        }

    }




