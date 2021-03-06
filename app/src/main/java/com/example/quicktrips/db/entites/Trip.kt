package com.example.quicktrips.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_table")
data class Trip(
    // Todo: look into if have to use Embedded objects (Maybe for display later)
    var mTripLocation: String,
    var mTripTimePeriod: String,
    var TripDangerLevel: Int,
    var mUserIdTrip: Int,
    var mLocationIdTrip: Int
) {
    @PrimaryKey
    var mTripId: Int? = null
    var hasTravelled: Boolean = false
    var mUsernameTrip: String = ""
    var mUserReview: String = "No review yet"

}