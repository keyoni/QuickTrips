package com.example.quicktrips.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quicktrips.db.entites.Location
import com.example.quicktrips.db.entites.Trip
import com.example.quicktrips.db.entites.User
import com.example.quicktrips.db.repos.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(
    private val repository: AppRepository
): ViewModel() {

    var mAllUsers = repository.getUsers()
    var mAllLocations = repository.getLocations()

    // From Users

   // var loggedInUser = repository.getUserByUsernameAndPassword()

    fun insert(user: User) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(user)
    }

    fun update(user: User) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(user)
    }

    fun delete(user: User) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(user)
    }

    fun getUsers() = CoroutineScope(Dispatchers.Main).launch {
        repository.getUsers()
    }

    fun getUsersById(userId: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.getUsersById(userId)
    }

    fun getUserByUsernameAndPassword (username: String, password: String) {
        repository.getUserByUsernameAndPassword(username,password)
    }

    //From Trips

    fun insert(trip: Trip) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(trip)
    }

    fun update(trip: Trip) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(trip)
    }

    fun delete(trip: Trip) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(trip)
    }

    fun getTrips() = CoroutineScope(Dispatchers.Main).launch {
        repository.getTrips()
    }

    fun getUserPendingTrips(travelled: Boolean, userId: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.getUserPendingTrips(travelled,userId)
    }
    fun getUserTravelledTrips(travelled: Boolean, userId: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.getUserTravelledTrips(travelled,userId)
    }


    //From Locations

    fun insert(location: Location) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(location)
    }

    fun update(location: Location) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(location)
    }

    fun delete(location: Location) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(location)
    }

   // fun getLocations() = CoroutineScope(Dispatchers.Main).launch {
     //   repository.getLocations()
    //}

    fun getLocationsById(locationId: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.getLocationsById(locationId)
    }

}
