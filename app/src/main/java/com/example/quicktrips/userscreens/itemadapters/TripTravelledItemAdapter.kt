package com.example.quicktrips.userscreens.itemadapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TripTravelledItemAdapter.TripTravelledViewHolder, position: Int) {

        var currentTrip = mAllTrips[position]
        holder.itemView.apply {
            tvTripTravelled.text = currentTrip.mTripLocation
            tvTripTimePeriodTravelled.text = currentTrip.mTripTimePeriod
            //tvTripDangerTravelled.text = currentTrip.TripDangerLevel.toString()

        }
        //Should make new adapter or change name of this adapter but for now
        if (mAdminProfile) {
            if (currentTrip.hasTravelled) {
                holder.itemView.apply {
                    tvTripTravelled.setTextColor(Color.parseColor("#7BBF5E"))
                    tvTripTimePeriodTravelled.setTextColor(Color.parseColor("#7BBF5E"))
                    // tvTripDangerTravelled.setTextColor(R.color.colorGreen)

                    setOnClickListener() {
                        val alertDialog1: AlertDialog.Builder = AlertDialog.Builder(context)
                        alertDialog1.setTitle("Review of ${currentTrip.mTripLocation}!")
                        alertDialog1.setMessage("${currentTrip.mUserReview}")
                        alertDialog1.setPositiveButton("Read") { _, _ -> }
                        val alert = alertDialog1.create()
                        alert.setCanceledOnTouchOutside(
                            true
                        )
                        alert.show()

                    }
                }

            } else {
                holder.itemView.apply {
                    tvTripTravelled.setTextColor(Color.parseColor("#BF5E6A"))
                    tvTripTimePeriodTravelled.setTextColor(Color.parseColor("#BF5E6A"))
                    //tvTripDangerTravelled.setTextColor(R.color.colorRed)
                }
            }
        } else {

            holder.itemView.apply {
                setOnClickListener() {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertDialog.setTitle("Review Time!")
                    alertDialog.setPositiveButton("Add/Edit Review") { _, _ ->
                        // navas to update review page
                        //send over trip id in bundle

                        val bundle = bundleOf("tripIdReview" to currentTrip.mTripId!!)
                        Navigation.findNavController(it)
                            .navigate(R.id.navigate_to_updateReviewFragment, bundle)

                    }
                    alertDialog.setNegativeButton("Cancel") { _, _ -> }
                    val alert = alertDialog.create()
                    alert.setCanceledOnTouchOutside(
                        true
                    )
                    alert.show()
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return mAllTrips.size
    }
        
  }
