package com.example.quicktrips.db.repos

import androidx.room.Query
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.entites.Location
import com.example.quicktrips.db.entites.Trip
import com.example.quicktrips.db.entites.User

class AppRepository(
    private val db: AppDatabase
) {
    // From User Dao

    suspend fun insert(user: User) = db.getUserDao().insert(user)

    suspend fun update(user: User) = db.getUserDao().update(user)

    suspend fun delete(user: User) = db.getUserDao().delete(user)

    suspend fun getUsers(): List<User> = db.getUserDao().getUsers()

    suspend fun getUsersById(userId: Int): User = db.getUserDao().getUsersById(userId)

    // from Trip Dao

    suspend fun insert(trip: Trip) = db.getTripDao().insert(trip)

    suspend fun update(trip: Trip) = db.getTripDao().update(trip)

    suspend fun delete(trip: Trip) = db.getTripDao().delete(trip)

    suspend fun getTrips(): List<Trip> = db.getTripDao().getTrips()

    suspend fun getUserPendingTrips(travelled: Boolean, userId: Int): List<Trip> = db.getTripDao().getUserPendingTrips(travelled,userId)

    suspend fun getUserTravelledTrips(travelled: Boolean, userId: Int): List<Trip> = db.getTripDao().getUserPendingTrips(travelled,userId)

    // From Location Dao
    suspend fun insert(location: Location) = db.getLocationDao().insert(location)

    suspend fun update(location: Location) = db.getLocationDao().update(location)

    suspend fun delete(location: Location) = db.getLocationDao().delete(location)

    suspend fun getLocations(): List<Location> = db.getLocationDao().getLocations()

    suspend fun getLocationsById(locationId: Int): Location = db.getLocationDao().getLocationsById(locationId)






}