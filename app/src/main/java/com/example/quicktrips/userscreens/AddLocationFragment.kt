package com.example.quicktrips.userscreens

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
import com.example.quicktrips.databinding.FragmentAddLocationBinding
import com.example.quicktrips.databinding.FragmentLocationsBinding
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.entites.Location
import com.example.quicktrips.db.entites.User
import com.example.quicktrips.db.repos.AppRepository

class AddLocationFragment : Fragment(R.layout.fragment_add_location) {

    private lateinit var binding: FragmentAddLocationBinding
    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddLocationBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        // Code adapted from Stevdza-San (https://www.youtube.com/watch?v=UBCAWfztTrQ)
        //ToDo: Refactor to DRY
        val addButton = arguments?.getBoolean("AddButton")
        if (addButton == true) {
            binding.btnAddLoc.text = "Add"
        } else {

            //Updating Location
            binding.btnAddLoc.text = "Update"
            //val locationId = arguments?.getInt("LocationId")
            mViewModel.getLocationsById(arguments?.getInt("LocationId")!!)
            mViewModel.mCurrentLocation.observe(viewLifecycleOwner, Observer {
                binding.etLocationName.setText(it[0].mLocationName)
                binding.etLocationTimePeriod.setText(it[0].mTimePeriod)
                binding.etLocationLevelOfDanger.setText(it[0].mDangerLevel.toString())
                binding.etLocationDescription.setText(it[0].mShortDescription)

            })

        }
        binding.btnAddLoc.setOnClickListener() {
            val locationName = binding.etLocationName.text.toString()
            val locTimePeriod = binding.etLocationTimePeriod.text.toString()
            val locLevelOfDanger = binding.etLocationLevelOfDanger.text.toString()
            val locDescription = binding.etLocationDescription.text.toString()


            if (locationName != "" && locTimePeriod != "" && locDescription != "" && locLevelOfDanger != "") {
                val newLocation =
                    Location(locationName, locTimePeriod, locLevelOfDanger.toInt(), locDescription)
                if (addButton == true) {
                    mViewModel.insert(newLocation)
                    Toast.makeText(context, "They'll love it here! $locationName Added!", Toast.LENGTH_LONG).show()
                } else {
                    //todo: Maybe how to fix make update queary with just get location by id then update each line i guess
                    mViewModel.update(newLocation)

                    Toast.makeText(context, "$locationName Updated!", Toast.LENGTH_LONG).show()
                }
                Navigation.findNavController(it).navigate(R.id.navigate_to_location_main)
            } else {
                Toast.makeText(context, "Please enter all information!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}