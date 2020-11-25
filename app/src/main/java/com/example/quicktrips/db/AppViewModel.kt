package com.example.quicktrips.db

import androidx.lifecycle.ViewModel
import com.example.quicktrips.db.entites.User
import com.example.quicktrips.db.repos.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(
    private val repository: AppRepository
): ViewModel() {

    // From Users
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

}