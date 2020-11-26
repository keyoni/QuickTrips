package com.example.quicktrips

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.quicktrips.databinding.FragmentSignUpBinding
import com.example.quicktrips.db.AppDatabase
import com.example.quicktrips.db.UserDao
import com.example.quicktrips.db.entites.User


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding

    //var mUserDao : UserDao? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.btnSignUp.setOnClickListener(){
            val username = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val isDoctor = binding.chDoctor.isChecked
            Log.d("SIGNIN","$username, $password,$firstName,$lastName,$isDoctor")
            if(username != "" && password != "" && firstName != "" && lastName != "" ) {
                val newUser = User(username, password, firstName, lastName, isDoctor)
                Toast.makeText(context,"Welcome to the TARDIS, please log in!",Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).navigate(R.id.navigate_to_signInFragment)
            } else {
                Toast.makeText(context,"Please enter fill out all information!",Toast.LENGTH_SHORT).show()
            }
        }
    }


}