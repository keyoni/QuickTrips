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
//make querey to update and make a colum name for each color and put set to whatever is passed in
    @Query("UPDATE trip_table SET hasTravelled = :travelled WHERE mTripId = :tripId ")
    suspend fun updateTravelled(travelled: Boolean, tripId: Int)

    @Query("SELECT * FROM trip_table WHERE hasTravelled = :travelled")
    fun getAllTravelledTrips(travelled: Boolean): LiveData<List<Trip>>

    @Query("SELECT * FROM trip_table where mUserIdTrip = :userId")
    fun getAllUserTrips(userId: Int): LiveData<List<Trip>>

    @Query("SELECT * FROM trip_table where mTripId = :tripId")
    fun getTripById(tripId: Int): LiveData<List<Trip>>

    @Query("UPDATE trip_table  SET mUserReview = :reviewNew WHERE mTripId = :tripId")
    suspend fun  updateReview( reviewNew: String, tripId: Int)
}