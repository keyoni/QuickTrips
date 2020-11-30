package com.example.quicktrips.userscreens.itemadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        } else {
            holder.itemView.btnLocTrip.visibility = View.VISIBLE
        }

        var currentLocation = mAllLocations[position]
        holder.itemView.apply {
            tvLocTitle.text = currentLocation.mLocationName
            tvLocTimePeriod.text = currentLocation.mTimePeriod
            tvLocDanger.text = currentLocation.mDangerLevel.toString()
            tvLocDescription.text = currentLocation.mShortDescription

            btnLocTrip.setOnClickListener(){
                var newTrip = Trip(currentLocation.mLocationName,currentLocation.mTimePeriod,currentLocation.mDangerLevel,mUserId)
                mViewModel.insert(newTrip)
                mViewModel.delete(currentLocation)
            }

        }

    }

    override fun getItemCount(): Int {
       return mAllLocations.size
    }
}