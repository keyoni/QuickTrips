package com.example.quicktrips

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.AppViewModelFactory
import com.example.quicktrips.db.UserDao
import com.example.quicktrips.db.entites.User
import com.example.quicktrips.db.repos.AppRepository


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var mViewModel: AppViewModel
    var mUserDao : UserDao? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        val database = AppDatabase(requireContext())
        val repository = AppRepository(database)
        val factory = AppViewModelFactory(repository)
        mViewModel = ViewModelProvider(this,factory).get(AppViewModel::class.java)

        // Code adapted from Stevdza-San (https://www.youtube.com/watch?v=UBCAWfztTrQ)
        binding.btnSignUp.setOnClickListener(){ it ->
            val username = binding.etUserName.text.toString()
            val userPassword = binding.etPassword.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val isDoctor = binding.chDoctor.isChecked

            //Log.d("SIGNIN","$username, $password,$firstName,$lastName,$isDoctor")
            if(username != "" && userPassword != "" && firstName != "" && lastName != "" ) {
                //ToDo: UseCASE: Username wrong length
                if (username.length < 4 || username.length > 10) {
                    Toast.makeText(context, getString(R.string.too_short_user), Toast.LENGTH_SHORT)
                        .show()
                    //ToDo: UseCase: Password too short
                } else if (userPassword.length < 4) {
                    Toast.makeText(
                        context,
                        getString(R.string.too_short_password),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // ToDo: UserCase Username Taken!
                    //Log.d("SIGNUP","usersafe = $userSafe")
                    mViewModel.getUserByUserName(username)

                    mViewModel.mUserCheck.observe(viewLifecycleOwner, Observer   {
                        if (it.isNotEmpty()) {

                            //Log.d("SIGNUP","IN NOT EMPTY, USERSAFE = $userSafe")
                            Toast.makeText(
                                context,
                                getString(R.string.username_taken),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            //Log.d("SIGNUP","CREATING NEW USER, USERSAFE = $userSafe")
                            val newUser =
                                User(username, userPassword, firstName, lastName, isDoctor)
                            mViewModel.insert(newUser)
                            Toast.makeText(
                                context,
                                getString(R.string.new_user_welcome),
                                Toast.LENGTH_LONG
                            ).show()
                            Navigation.findNavController(view)
                                .navigate(R.id.navigate_to_signInFragment)

                        }
                    })
                }
            }else {
                Toast.makeText(context,"Please enter all information!",Toast.LENGTH_SHORT).show()
            }
        }
    }


}