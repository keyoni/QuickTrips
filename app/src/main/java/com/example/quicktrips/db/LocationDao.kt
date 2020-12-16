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

    //Add Query get location by danger level
}