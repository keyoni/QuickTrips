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
import androidx.navigation.Navigation
import com.example.quicktrips.R
import com.example.quicktrips.databinding.FragmentUpdateBioBinding
import com.example.quicktrips.databinding.FragmentUpdateReviewBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.repos.AppRepository
import kotlinx.android.synthetic.main.activity_main.*


class UpdateReviewFragment : Fragment(R.layout.fragment_update_review) {
    private lateinit var binding: FragmentUpdateReviewBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateReviewBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        val currentUserId = sharedPref.getInt("current_user_id", -1)

        // find trips by id
        val tripId = arguments?.getInt("tripIdReview")!!
        mViewModel.getTripById(tripId)
        mViewModel.mCurrentTrip.observe(viewLifecycleOwner, Observer {
            binding.tvUpdateTitleReview.text = "How was your TARDIS Journey to ${it[0].mTripLocation}?"
            binding.etUpdateReview.setText(it[0].mUserReview)
        })
        // save button saves new review
        binding.btnSaveReview.setOnClickListener(){
            val newReview = binding.etUpdateReview.text.toString()

            if (newReview == ""){
                Toast.makeText(context, "Please give some honest feedback! Also, be kind!", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.updateReview(newReview,(arguments?.getInt("tripIdReview")!!))
                Toast.makeText(context, "I hope you enjoyed your travels!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).navigate(R.id.navigate_to_profileFragment_from_update_review)
            }
        }


        binding.btnCancelReview.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.navigate_to_profileFragment_from_update_review)
        }

    }
}