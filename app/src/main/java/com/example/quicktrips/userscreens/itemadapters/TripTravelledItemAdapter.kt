package com.example.quicktrips.userscreens.itemadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktrips.R
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.entites.Trip
import kotlinx.android.synthetic.main.trip_pending_item.view.*
import kotlinx.android.synthetic.main.trip_travelled_item.view.*

class TripTravelledItemAdapter(
    val mViewModel: AppViewModel,
    var mAllTrips: List<Trip>,
    val mUserStatus: Int
// todo: pass current user
): RecyclerView.Adapter<TripTravelledItemAdapter.TripTravelledViewHolder>(){

    inner class TripTravelledViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripTravelledItemAdapter.TripTravelledViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_travelled_item,parent,false)
        return TripTravelledViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripTravelledItemAdapter.TripTravelledViewHolder, position: Int) {

        var currentTrip = mAllTrips[position]
        holder.itemView.apply {
            tvTripTravelled.text = currentTrip.mTripLocation
            tvTripTimePeriodTravelled.text = currentTrip.mTripTimePeriod
            tvTripDangerTravelled.text = currentTrip.TripDangerLevel.toString()

        }

    }

    override fun getItemCount(): Int {
        return mAllTrips.size
    }
        
  }
