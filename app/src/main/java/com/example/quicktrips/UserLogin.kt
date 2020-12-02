package com.example.quicktrips

import android.content.Context
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.entites.User

class UserLogin(
    private  var mViewModel: AppViewModel,
    private var mCurrentUser : User,
    private var context: Context
) {

    val sharedPref = context.getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    val intToDoctor = if (mCurrentUser.mIsDoctor) 1 else 0

    var userId = editor.putInt("current_user_id", mCurrentUser.mUserId!!).apply()
    var userisDoctor = editor.putInt("current_user_isDoctor", intToDoctor).apply()


    fun login() {

        sharedPref ?:

        return with(editor)
        {
            putInt("current_user_id", mCurrentUser.mUserId!!)
            putInt("current_user_isDoctor", intToDoctor)
            commit()
        }
    }



}