package com.example.quicktrips

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quicktrips.databinding.FragmentSignInBinding

import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.entites.User
import com.example.quicktrips.db.repos.AppRepository
import com.example.quicktrips.userscreens.UserActivity


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding

    private lateinit var mViewModel: AppViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, factory).get(AppViewModel::class.java)

        val sharedPref = requireContext().getSharedPreferences("myAppPref", Context.MODE_PRIVATE)
        var currentUserId = sharedPref.getInt("current_user_id", -1)
        var currentUserStatus = sharedPref.getInt("current_user_isDoctor", -1)

        Log.d("SIGNIN", " on start Userid: $currentUserId")
        if (currentUserId > 0 && currentUserStatus >= 0) {
            val intentLoggedIn = Intent(activity, UserActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnSignIn.setOnClickListener() {
            //add UserID as extra then use to connect to account profile page and if Doctor for admin
            val username = binding.etUserName.text.toString()
            val userPassword = binding.etPassword.text.toString()


//
            if (username != "" && userPassword != "") {
                signIn(username, userPassword)

            }
            currentUserId = sharedPref.getInt("current_user_id", -1)
            currentUserStatus = sharedPref.getInt("current_user_isDoctor",-1)

            if (currentUserId > 0 && currentUserStatus >= 0) {
                val intent = Intent(activity, UserActivity::class.java).also {
                    startActivity(it)
                }
            }

        }

        binding.btnHomeSignUp.setOnClickListener() {
            Navigation.findNavController(it).navigate(R.id.navigate_to_signUpFragment)
        }
    }

    private fun signIn(username: String, userPassword: String)
    {
        mViewModel.getUserByUsernameAndPassword(username,userPassword)
        //todo: maybe make new class
        mViewModel.mCurrentUser.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                Toast.makeText(
                    context,
                    "TARDIS Security alerted, please try again ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Welcome To The TARDIS ${it[0].mFirstName}", Toast.LENGTH_SHORT).show()
               var userLog = UserLogin(mViewModel,it[0],requireContext())
                userLog.login()
            }

        })
    }


}