package com.tm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment_task.sample_test.R
import com.assessment_task.sample_test.utill.OnModelClickListener
import com.tm.model.User
import kotlinx.android.synthetic.main.item_user_info.view.*

class UserInfoDataAdapter(val users : List<User>, val onModelClick: OnModelClickListener<User>) : RecyclerView.Adapter<UserInfoDataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_user_info, parent, false)
        )
    }

    override fun getItemCount() = users.size


    class DataViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.view.userId.text = users[position].id.toString()
        holder.view.userName.text = users[position].name
        holder.view.userMail.text = users[position].email
        holder.view.userPhone.text = users[position].phone

        holder.view.setOnClickListener {
            onModelClick.onClick(users[position])
        }
    }
}