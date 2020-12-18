package com.example.quicktrips.userscreens.itemadapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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
    val mUserStatus: Int,
    val lifecycleOwner: LifecycleOwner
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
            holder.itemView.ivLocDelete.visibility = View.VISIBLE
        } else {
            holder.itemView.btnLocTrip.visibility = View.VISIBLE
            holder.itemView.ivEdit.visibility = View.INVISIBLE
            holder.itemView.ivLocDelete.visibility = View.INVISIBLE
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

                mViewModel.getAllUserTrips(mUserId)
                mViewModel.mCurrentUserAllTrips.observe(lifecycleOwner, Observer {
                    var locationTravelled = false
                    for( trip in it) {
                        if (currentLocation.mLocationId == trip.mLocationIdTrip) {
                            Toast.makeText(
                                context,
                                "You've already travelled here, or you will. Time is wibbly wobbly.",
                                Toast.LENGTH_SHORT
                            ).show()
                            locationTravelled = true
                            break
                        }
                    }
                    if(!locationTravelled) {
                        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                        alertDialog.setTitle("Add Trip!")
                        alertDialog.setMessage("Are you sure you want to travel to ${currentLocation.mLocationName}?")
                        alertDialog.setPositiveButton("Yes") { _, _ ->
                            var newTrip = Trip(
                                currentLocation.mLocationName,
                                currentLocation.mTimePeriod,
                                currentLocation.mDangerLevel,
                                mUserId,
                                currentLocation.mLocationId!!.toInt()
                            )
                            mViewModel.insert(newTrip)
                            //mViewModel.delete(currentLocation)
                            Toast.makeText(
                                context,
                                "Allons-y! ${currentLocation.mLocationName} has added to your Pending Trips",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        alertDialog.setNegativeButton("No") { _, _ -> }
                        val alert = alertDialog.create()
                        alert.setCanceledOnTouchOutside(
                            true
                        )
                        alert.show()
                    }
                })
                }


                //todo: Add dialog box to comfrom trip, extra dialong for danger 3+. mayve only danger 3+ if user has travelled on at least 2 trips


            ivEdit.setOnClickListener(){
                val bundle = bundleOf("LocationId" to currentLocation.mLocationId )
                Navigation.findNavController(it).navigate(R.id.navigate_to_add_location,bundle)
            }

            ivLocDelete.setOnClickListener(){
                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialog.setTitle("Location Remove Confirmation")
                alertDialog.setMessage("Are you sure you want to remove  ${currentLocation.mLocationName}?")
                alertDialog.setPositiveButton("Yes") {_,_ ->
                    mViewModel.delete(currentLocation)
                    Toast.makeText(context, "${currentLocation.mLocationName} has been removed", Toast.LENGTH_SHORT).show()
                }
                alertDialog.setNegativeButton("No"){_,_ ->}
                val alert  = alertDialog.create()
                alert.setCanceledOnTouchOutside(
                    true
                )
                alert.show()

            }

        }

    }

    override fun getItemCount(): Int {
       return mAllLocations.size
    }
}