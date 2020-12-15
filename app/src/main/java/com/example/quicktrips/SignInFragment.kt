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
import com.example.quicktrips.db.entites.Location
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

        // App starting data if no users
        // Adds default users and locations to app
        mViewModel.getUsers()
        mViewModel.mAllUsers.observeForever(){
            if(it.isEmpty()){
               appStartingData()
            }
        }

        Log.d("SIGNIN", " on start Userid: $currentUserId")
        // logged in persistence
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

    private fun appStartingData()
    {
        // Add Doctor and Two Companions
        mViewModel.insert(User("TheDoctor","doctor","The","Doctor", true))
        mViewModel.insert(User("amyP","FFaC11","Amy","Pond", false))
        mViewModel.insert( User("bPotts","fries","Bill","Potts", false))

        // Add default starting locations (5) - 5 test
        mViewModel.insert(Location("Earth", "21th Century",1,"2052 is a fun year! Let's go find out why!"))
        mViewModel.insert(Location("Axista Four","26th Century",1,"Independent Earth Colony, it's been a while since I visited."))
        mViewModel.insert(Location("Hyspero","96th Century", 2,"Magical place with many different landmasses connected by gateways. Fun play to explore"))
        mViewModel.insert(Location("Clom","36th Century",1,"Every wanted to go to Disneyland on another planet? Though, it's probably the only thing Clom has to offer."))
        mViewModel.insert(Location("Messaline","61th Century",2,"Humans and the Hath had a war. There's peace now, let's go see what they've made of it."))
        mViewModel.insert(Location("New Earth","50th Century",2,"I've seen New New York a few times. Let's go check out New London!"))
        mViewModel.insert(Location("Test1","Test Century",1,"This is a test"))
        mViewModel.insert(Location("Test2","Test Century",3,"This is a test"))
        mViewModel.insert(Location("Test3","Test Century",5,"This is a test"))
        mViewModel.insert(Location("Pending Test4","Test Century",2,"This is a Pending test"))
        mViewModel.insert(Location("Pending Test5","Test Century",5,"This is a Pending test"))
    }


}