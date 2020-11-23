package com.assessment_task.sample_test.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.assessment_task.sample_test.R
import com.assessment_task.sample_test.utill.OnModelClickListener
import com.tm.model.User
import com.tm.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var onModelClick: OnModelClickListener<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.fetchUserData();
        observeUserList()
    }

    private fun observeUserList() {
        userViewModel.getUserListLiveData()?.observe(this, Observer {
            Log.w("list", it.toString());
            //loadUserDataRv(it)
        })
    }

}