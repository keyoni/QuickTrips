package com.example.quicktrips.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quicktrips.db.entites.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_table ORDER BY mLastName DESC" )
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE mUserId = :userId ")
    suspend  fun getUsersById(userId: Int): User

   @Query("SELECT * FROM user_table WHERE mUserName = :username AND mPassword = :password ")
    fun getUsersByUsernameAndPassword(username: String, password: String): LiveData<List<User>>

}