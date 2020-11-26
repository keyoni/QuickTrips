package com.example.quicktrips

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        mViewModel = ViewModelProvider(this,factory).get(AppViewModel::class.java)


        binding.btnSignIn.setOnClickListener() {
            //add UserID as extra then use to connect to account profile page and if Doctor for admin
            val username = binding.etUserName.text.toString()
            val userPassword = binding.etPassword.text.toString()
//
//            if(username != "" && userPassword != "") {
//              //  val user: User? = signIn(username, userPassword)
//            }
            val intent = Intent(activity,UserActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnHomeSignUp.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.navigate_to_signUpFragment)
        }
    }
//
//    private fun signIn(username: String, userPassword: String) : User
//    {
//        val userID = mViewModel.getUserByNameAndPassword(username,userPassword)
//        return user
//
//    }


}