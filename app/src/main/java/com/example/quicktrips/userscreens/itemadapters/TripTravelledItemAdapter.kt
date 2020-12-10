package com.example.quicktrips.userscreens.itemadapters

import android.graphics.Color
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
    val mUserStatus: Int,
    val mAdminProfile: Boolean
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

        //Should make new adapter or change name of this adapter but for now
        if(mAdminProfile) {
            if (currentTrip.hasTravelled) {
                holder.itemView.apply {
                    tvTripTravelled.setTextColor(Color.GREEN)
                    tvTripTimePeriodTravelled.setTextColor(Color.GREEN)
                    tvTripDangerTravelled.setTextColor(Color.GREEN)
                }
            } else {
                holder.itemView.apply {
                    tvTripTravelled.setTextColor(Color.RED)
                    tvTripTimePeriodTravelled.setTextColor(Color.RED)
                    tvTripDangerTravelled.setTextColor(Color.RED)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return mAllTrips.size
    }
        
  }
