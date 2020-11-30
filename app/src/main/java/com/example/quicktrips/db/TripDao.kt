package com.example.quicktrips.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quicktrips.db.entites.Trip

@Dao
interface TripDao {
    @Insert
    suspend fun insert(trip: Trip)

    @Update
    suspend fun update(trip: Trip)

    @Delete
    suspend fun delete(trip: Trip)

    @Query("SELECT * FROM trip_table" )
    fun getTrips(): LiveData<List<Trip>>

//    @Query("SELECT * FROM trip_table WHERE mTripId = :tripId ")
//    suspend fun getTripsById(tripId: Int): Trip

    @Query("SELECT * FROM trip_table WHERE hasTravelled = :travelled AND mUserIdTrip = :userId" )
    fun getUserPendingTrips(travelled: Boolean, userId: Int): LiveData<List<Trip>>

    @Query("SELECT * FROM trip_table WHERE hasTravelled = :travelled AND mUserIdTrip = :userId" )
    fun getUserTravelledTrips(travelled: Boolean, userId: Int): LiveData<List<Trip>>
}