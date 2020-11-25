package com.example.quicktrips.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_table")
data class Trip(
    // Todo: look into if have to use Embedded objects (Maybe for display later)
    var mLocationIdTrip: Int,
    var mUserIdTrip: Int,
) {
    @PrimaryKey
    var mTripId: Int? = null
    var hasTravelled: Boolean = false
}