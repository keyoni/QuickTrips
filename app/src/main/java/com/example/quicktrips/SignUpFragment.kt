package com.example.quicktrips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
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
        binding.btnSignUp.setOnClickListener(){
            val username = binding.etUserName.text.toString()
            val userPassword = binding.etPassword.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val isDoctor = binding.chDoctor.isChecked

            //Log.d("SIGNIN","$username, $password,$firstName,$lastName,$isDoctor")
            if(username != "" && userPassword != "" && firstName != "" && lastName != "" ) {
                val newUser = User(username, userPassword, firstName, lastName, isDoctor)
                mViewModel.insert(newUser)
                Toast.makeText(context,"Welcome to the TARDIS, please log in!",Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).navigate(R.id.navigate_to_signInFragment)
            } else {
                Toast.makeText(context,"Please enter all information!",Toast.LENGTH_SHORT).show()
            }
        }
    }


}