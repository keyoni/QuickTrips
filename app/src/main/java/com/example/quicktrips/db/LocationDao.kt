package com.example.quicktrips.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quicktrips.db.entites.Location

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(location: Location)

    @Update
    suspend fun update(location: Location)

    @Delete
    suspend  fun delete(location: Location)

    @Query("SELECT * FROM location_table ORDER BY mDangerLevel" )
    fun getLocations(): LiveData<List<Location>>

    @Query("SELECT * FROM location_table WHERE mLocationId = :locationId ")
     fun getLocationsById(locationId: Int): LiveData<List<Location>>

     @Query("UPDATE location_table SET mLocationName = :locationName, mTimePeriod = :timePeriod, mDangerLevel = :dangerLevel, mShortDescription =:description WHERE mLocationId = :locationId")
     suspend fun updateLocationFull(locationName: String, timePeriod: String, dangerLevel: Int, description: String, locationId: Int)

     @Query("UPDATE location_table SET mDoctorTravelled = :doctorTravelled WHERE mLocationId = :locationId")
     suspend fun updateDoctorTravelled(doctorTravelled: Int, locationId: Int)
    //Add Query get location by danger level
}