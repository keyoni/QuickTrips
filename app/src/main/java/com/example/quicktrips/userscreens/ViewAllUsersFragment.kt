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
import com.example.quicktrips.databinding.FragmentProfileBinding
import com.example.quicktrips.databinding.FragmentViewAllUsersBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository
import com.example.quicktrips.userscreens.itemadapters.UserItemAdapter
import kotlinx.coroutines.awaitAll


class ViewAllUsersFragment : Fragment(R.layout.fragment_view_all_users) {

    private lateinit var binding: FragmentViewAllUsersBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewAllUsersBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        val currentUserId = sharedPref.getInt("current_user_id", -1)
        val currentUserStatus = sharedPref.getInt("current_user_isDoctor", -1)

        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        displayUsers()

    }

    private fun displayUsers() {
        var adapter = UserItemAdapter(mViewModel, listOf())
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.adapter = adapter


        mViewModel.mAllUsers.observeForever(){
            adapter.mAllUsers = it
            adapter.notifyDataSetChanged()
        }
    }
}
