package com.example.quicktrips.userscreens.itemadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktrips.R
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.entites.Trip
import kotlinx.android.synthetic.main.fragment_add_location.view.*
import kotlinx.android.synthetic.main.locationitem.view.*
import kotlinx.android.synthetic.main.trip_pending_item.view.*
import kotlinx.coroutines.withContext
import java.security.AccessController.getContext

class TripItemAdapter(
    val mViewModel: AppViewModel,
    var mAllTrips: List<Trip>,
    val mUserStatus: Int
//todo: pass current user
): RecyclerView.Adapter<TripItemAdapter.TripViewHolder>(){

    inner class TripViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_pending_item,parent,false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        if (mUserStatus == 1) {
            holder.itemView.swTravelled.visibility = View.VISIBLE
        } else{
            holder.itemView.swTravelled.visibility = View.INVISIBLE
        }
        var currentTrip = mAllTrips[position]
        holder.itemView.apply {
            tvTripPending.text = currentTrip.mTripLocation
            tvTripTimePeriod.text = currentTrip.mTripTimePeriod
            tvTripDanger.text = currentTrip.TripDangerLevel.toString()

        }

    }

    override fun getItemCount(): Int {
        return mAllTrips.size
    }
}