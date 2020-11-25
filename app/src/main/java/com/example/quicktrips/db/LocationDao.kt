package com.example.quicktrips.db

import androidx.room.*
import com.example.quicktrips.db.entites.Location

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(vararg location: Location)

    @Update
    suspend fun update(vararg location: Location)

    @Delete
    suspend  fun delete(location: Location)

    @Query("SELECT * FROM location_table ORDER BY mLocationName" )
    suspend fun getLocations(): List<Location>

    @Query("SELECT * FROM location_table WHERE mLocationId = :locationId ")
    suspend fun getLocationsById(locationId: Int): Location

    //Add Query get location by danger level
}