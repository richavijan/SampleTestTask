package com.assessment_task.sample_test.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment_task.sample_test.R
import com.assessment_task.sample_test.utill.OnModelClickListener
import com.bumptech.glide.Glide
import com.tm.model.UserAlbum
import kotlinx.android.synthetic.main.item_user_album.view.*


class UserAlbumAdapter(val users: List<UserAlbum>,
                       val context: Context,
                       val onModelClick: OnModelClickListener<UserAlbum>) : RecyclerView.Adapter<UserAlbumAdapter.UserAlbumViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAlbumViewHolder {
        return UserAlbumViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_user_album, parent, false)
        )
    }

    override fun getItemCount() = users.size


    class UserAlbumViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: UserAlbumViewHolder, position: Int) {
        holder.view.albumTitle.text = users[position].thumbnailUrl
        Glide
        .with(context)
        .asBitmap()
        .placeholder(R.drawable.ic_launcher_foreground)
        .load(users[position].thumbnailUrl)
        .into(holder.view.albumThumbnilUrl)

        holder.view.setOnClickListener {
            onModelClick.onClick(users[position])
        }
    }
}