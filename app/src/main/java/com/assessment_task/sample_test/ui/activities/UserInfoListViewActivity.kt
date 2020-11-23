package com.assessment_task.sample_test.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment_task.sample_test.R
import com.assessment_task.sample_test.utill.ALBUM_ID
import com.assessment_task.sample_test.utill.OnModelClickListener
import com.tm.model.User
import com.tm.ui.adapters.UserInfoDataAdapter
import com.tm.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_users_info_list_viee.*

class UserInfoListViewActivity : AppCompatActivity() {


    lateinit var userViewModel: UserViewModel
    lateinit var onModelClick: OnModelClickListener<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_info_list_viee)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.fetchUserData();
        observeUserList()

        onModelClick = object : OnModelClickListener<User> {
            override fun onClick(model: User) {
                Log.w("userData", model.toString());
                startAlbumListActivity(model)
            }
        }
    }

    private fun startAlbumListActivity(model: User) {
        val intent = Intent(this@UserInfoListViewActivity,
        UserAlbumListInfoActivity::class.java)
        intent.putExtra(ALBUM_ID, model.id)
        startActivity(intent)
    }

    private fun observeUserList() {
        userViewModel.getUserListLiveData()?.observe(this, Observer {
            loadUserDataRv(it)
        })
    }

    fun loadUserDataRv(usersData: List<User>) {
        rv_user_info.layoutManager = LinearLayoutManager(this)
        rv_user_info.adapter = UserInfoDataAdapter(usersData, onModelClick)
    }

}