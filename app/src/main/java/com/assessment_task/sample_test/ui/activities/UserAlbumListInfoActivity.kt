package com.assessment_task.sample_test.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment_task.sample_test.R
import com.assessment_task.sample_test.ui.adapters.UserAlbumAdapter
import com.assessment_task.sample_test.utill.ALBUM_ID
import com.assessment_task.sample_test.utill.OnModelClickListener
import com.assessment_task.sample_test.utill.USER_ID
import com.tm.model.User
import com.tm.model.UserAlbum
import com.tm.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_user_album_list.*

class UserAlbumListInfoActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var onModelClick: OnModelClickListener<UserAlbum>

    var albumData: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_album_list)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        albumData = intent.extras?.getInt(ALBUM_ID)
        tv_albumId.text = albumData.toString()
        userViewModel.fetchAlbumDataData(albumData!!)
        observeUserList()

        onModelClick = object : OnModelClickListener<UserAlbum> {
            override fun onClick(model: UserAlbum) {
                // pass intent to description page
                Log.w("album_click", model.toString())
                startAlbumListActivity(model = model)
            }
        }
    }

    private fun startAlbumListActivity(model: UserAlbum) {
        val intent = Intent(this@UserAlbumListInfoActivity,
                UserAlbumDetailActivity::class.java)
        intent.putExtra(ALBUM_ID, model.albumId)
        intent.putExtra(USER_ID, model.id)
        startActivity(intent)
    }

    fun observeUserList() {
        userViewModel.getUserAlbumListLiveData()?.observe(this, androidx.lifecycle.Observer {
            loadUserDataRv(it)
        })
    }

    fun loadUserDataRv(usersAlbum: List<UserAlbum>) {
        rl_user_album_list.layoutManager = LinearLayoutManager(this)
        rl_user_album_list.adapter = UserAlbumAdapter(usersAlbum, this, onModelClick)
    }

}