package com.example.quicktrips.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quicktrips.db.repos.AppRepository

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory (
    private val repository: AppRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(repository) as T
    }
}