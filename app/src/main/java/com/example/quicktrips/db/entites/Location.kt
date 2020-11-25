package com.example.quicktrips.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="location_table" )
data class Location(
    var mLocationName: String,
    var mTimePeriod: String,
    var mDangerLevel: Int,
    var mShortDescription: String
) {
    @PrimaryKey(autoGenerate = true)
    var mLocationId : Int? = null
}