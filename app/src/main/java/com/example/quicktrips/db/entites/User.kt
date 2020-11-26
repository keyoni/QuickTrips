package com.example.quicktrips.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    val mUserName: String,
    val mPassword: String,
    val mFirstName: String,
    val mLastName: String,
    var mIsDoctor: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var mUserId : Int? = null
    var mBio: String = "No Bio Yet"
}