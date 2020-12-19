package com.example.quicktrips.userscreens.itemadapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktrips.R
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.entites.User
import kotlinx.android.synthetic.main.locationitem.view.*
import kotlinx.android.synthetic.main.users_item.view.*

class UserItemAdapter(
    val mViewModel: AppViewModel,
    var mAllUsers: List<User>

):RecyclerView.Adapter<UserItemAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var currentUser = mAllUsers[position]
        var currentId = currentUser.mUserId

        if (!currentUser.mIsDoctor) {
            holder.itemView.apply {
                tvName.text = "${currentUser.mFirstName} ${currentUser.mLastName}"
                tvUsername.text = currentUser.mUserName
            }

        } else {
            holder.itemView.apply {
                tvName.text = "Doctor"
                tvUsername.text = "TheDoctor"

            }

        }
        
            holder.itemView.setOnClickListener() {
                val bundle = bundleOf("UserIdProfile" to currentUser.mUserId)
                Navigation.findNavController(it)
                    .navigate(R.id.navigate_to_viewUserProfileFragment, bundle)
            }
        holder.itemView.apply {
            ivUserDelete.setOnClickListener() {
                if (!currentUser.mIsDoctor) {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertDialog.setTitle("User ${currentUser.mUserName} is a Security Risk")
                    alertDialog.setMessage("Remove ${currentUser.mFirstName} ${currentUser.mLastName}?")

                    alertDialog.setPositiveButton("Yes, no second chances.") { _, _ ->
                        mViewModel.delete(currentUser)
                        Toast.makeText(
                            context,
                            "${currentUser.mFirstName} ${currentUser.mLastName} has been removed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    alertDialog.setNegativeButton("No, give them another chance.") { _, _ -> }
                    val alert = alertDialog.create()
                    alert.setCanceledOnTouchOutside(
                        true
                    )
                    alert.show()
                } else if (currentUser.mIsDoctor) {
                    Toast.makeText(context, "You cannot remove yourself from the timeline... again", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
         return mAllUsers.size
    }


}