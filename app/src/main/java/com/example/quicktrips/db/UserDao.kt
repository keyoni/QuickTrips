package com.example.quicktrips.db

import androidx.room.*
import com.example.quicktrips.db.entites.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(vararg user: User)

    @Update
    suspend fun update(vararg user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_table ORDER BY mLastName DESC" )
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM user_table WHERE mUserId = :userId ")
    suspend  fun getUsersById(userId: Int): User

}