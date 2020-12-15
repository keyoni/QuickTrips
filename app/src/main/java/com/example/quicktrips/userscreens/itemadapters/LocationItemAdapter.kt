package com.example.quicktrips.userscreens.itemadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktrips.R
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.entites.Location
import com.example.quicktrips.db.entites.Trip
import kotlinx.android.synthetic.main.fragment_add_location.view.*
import kotlinx.android.synthetic.main.locationitem.view.*
import kotlinx.coroutines.withContext
import java.security.AccessController.getContext

class LocationItemAdapter(
    val mViewModel: AppViewModel,
    var mAllLocations: List<Location>,
    val mUserId: Int,
    val mUserStatus: Int
//todo: pass current user
): RecyclerView.Adapter<LocationItemAdapter.LocationViewHolder>(){

    inner class LocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.locationitem,parent,false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {

        if(mUserStatus == 1)
        {
            holder.itemView.btnLocTrip.visibility = View.INVISIBLE
            holder.itemView.ivEdit.visibility = View.VISIBLE
        } else {
            holder.itemView.btnLocTrip.visibility = View.VISIBLE
            holder.itemView.ivEdit.visibility = View.INVISIBLE
        }

        var currentLocation = mAllLocations[position]
        // Adds Location info to Recycle Viewer
        holder.itemView.apply {
            tvLocTitle.text = currentLocation.mLocationName
            tvLocTimePeriod.text = currentLocation.mTimePeriod
            tvLocDanger.text = currentLocation.mDangerLevel.toString()
            tvLocDescription.text = currentLocation.mShortDescription

            // Adds Trip to User Pending Trips and removes location from db
            btnLocTrip.setOnClickListener(){
                var newTrip = Trip(currentLocation.mLocationName,currentLocation.mTimePeriod,currentLocation.mDangerLevel,mUserId)
                mViewModel.insert(newTrip)
                mViewModel.delete(currentLocation)


            }

            ivEdit.setOnClickListener(){
                val bundle = bundleOf("LocationId" to currentLocation.mLocationId )
                Navigation.findNavController(it).navigate(R.id.navigate_to_add_location,bundle)
            }



        }

    }

    override fun getItemCount(): Int {
       return mAllLocations.size
    }
}