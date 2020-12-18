package com.example.quicktrips.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    var mUserCheck = repository.getUsers()
    var mAllLocations = repository.getLocations()
    var mCurrentLocation = repository.getLocations()
    var mCurrentUser: LiveData<List<User>> = repository.getUsers()
    var mAllTrips = repository.getTrips()
    var mCurrentUserPendingTrips = repository.getTrips()
    var mCurrentUserTravelledTrips = repository.getTrips()
    var mCurrentUserAllTrips = repository.getTrips()
    var mAllTravelledTrips = repository.getTrips()

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

    fun getUsers() {
       mAllUsers =  repository.getUsers()
    }

    fun getUserByUserName(username: String){
        mUserCheck = repository.getUserbyUsername(username)
    }

    fun getUsersById(userId: Int) {
       mCurrentUser = repository.getUsersById(userId)
    }

    fun getUserByUsernameAndPassword (username: String, password: String) {
       mCurrentUser = repository.getUserByUsernameAndPassword(username,password)
    }

    fun updateBio(bioNew: String, userId: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.updateBio(bioNew,userId)
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

    fun getUserPendingTrips(travelled: Boolean, userId: Int) {
        mCurrentUserPendingTrips = repository.getUserPendingTrips(travelled,userId)
    }
    fun getUserTravelledTrips(travelled: Boolean, userId: Int) {
        mCurrentUserTravelledTrips = repository.getUserTravelledTrips(travelled,userId)
    }

    fun updateTravelled(travelled: Boolean, tripId: Int)  = CoroutineScope(Dispatchers.Main).launch {
        repository.updateTravelled(travelled, tripId)
    }
    fun getAllTravelledTrips(travelled: Boolean) {
        mAllTravelledTrips = repository.getAllTravelledTrips(travelled)
    }
    fun getAllUserTrips(userId: Int) {
        mCurrentUserAllTrips = repository.getAllUserTrips(userId)
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

    fun getLocationsById(locationId: Int) {
       mCurrentLocation = repository.getLocationsById(locationId)
    }

    fun updateLocationFull(locationName: String, timePeriod: String, dangerLevel: Int, description: String, locationId: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.updateLocationFull(locationName, timePeriod, dangerLevel, description, locationId)
    }

}
