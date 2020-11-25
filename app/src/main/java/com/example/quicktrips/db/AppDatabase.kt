package com.example.quicktrips.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quicktrips.db.entites.Location
import com.example.quicktrips.db.entites.Trip
import com.example.quicktrips.db.entites.User

@Database(entities = [User::class, Location::class, Trip::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getLocationDao() : LocationDao
    abstract fun getTripDao() : TripDao

    companion object{
        // Keeping one instance of the database
        // Code Adapted from Philipp Lackner
        @Volatile
        private var instance : AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "QUICKTRIP_DATABASE")
                .build()

    }
}