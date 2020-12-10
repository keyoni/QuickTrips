package com.example.quicktrips.userscreens.itemadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktrips.R
import com.example.quicktrips.db.AppViewModel
import com.example.quicktrips.db.entites.User
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
                tvUserName.text = currentUser.mUserName
                tvFName.text = currentUser.mFirstName
                tvLName.text = currentUser.mLastName
            }

        } else {
            holder.itemView.apply {
                tvUserName.text = "---"
                tvFName.text = "---"
                tvLName.text = "---"
            }

        }
        holder.itemView.setOnClickListener(){
            val bundle = bundleOf("UserIdProfile" to currentUser.mUserId)
            Navigation.findNavController(it).navigate(R.id.navigate_to_viewUserProfileFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
         return mAllUsers.size
    }


}