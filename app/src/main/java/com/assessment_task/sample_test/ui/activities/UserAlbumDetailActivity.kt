package com.assessment_task.sample_test.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.assessment_task.sample_test.R
import com.assessment_task.sample_test.utill.ALBUM_ID
import com.assessment_task.sample_test.utill.OnModelClickListener
import com.assessment_task.sample_test.utill.USER_ID
import com.bumptech.glide.Glide
import com.tm.model.UserAlbum
import com.tm.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_user_album_detail.*
import kotlinx.android.synthetic.main.item_user_album.view.*

class UserAlbumDetailActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var onModelClick: OnModelClickListener<UserAlbum>

    var albumData: Int? = null
    var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_album_detail)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        albumData = intent.extras?.getInt(ALBUM_ID)
        userId = intent.extras?.getInt(USER_ID)
        userViewModel.fetchAlbumDetail(albumData!!, userId!!)
        observeAlbumDetail()
    }

    private fun observeAlbumDetail() {
        userViewModel.getUserAlbumListUserIdLiveData()?.observe(this,
                androidx.lifecycle.Observer {
                    it?.first()?.let {
                        tv_photo_id.text = it.id.toString()
                        tv_album_id.text = it.albumId.toString()
                        tv_album_title.text = it.title
                        Glide
                                .with(this)
                                .asBitmap()
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .load(it.thumbnailUrl)
                                .into(iv_album)

                    }
        })
    }
}