package com.tm.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tm.model.User
import com.tm.model.UserAlbum
import com.tm.repository.MyApi
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var albumId = MutableLiveData<Int>()
    var photoId = MutableLiveData<Int>()
    var userAlbumTitle = MutableLiveData<String>()
    var userAlbumUrl = MutableLiveData<String>()
    var isNetworkAvailable = MutableLiveData<Boolean>()

    var userList: MutableLiveData<List<User>>? = MutableLiveData()
    var userAlbumList: MutableLiveData<List<UserAlbum>>? = MutableLiveData()
    var userAlbumListByUserId: MutableLiveData<List<UserAlbum>>? = MutableLiveData()

    fun getUserListLiveData(): LiveData<List<User>>? = userList
    fun getUserAlbumListLiveData(): LiveData<List<UserAlbum>>? = userAlbumList
    fun getUserAlbumListUserIdLiveData(): LiveData<List<UserAlbum>>? = userAlbumListByUserId


    fun fetchUserData() {
        MyApi().getUsersList().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.w("error", t.stackTrace.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.let {
                    userList?.value = it
                }
            }
        })
    }

    fun fetchAlbumDataData(albumId: Int) {
        MyApi().getUserAlbumListByUserId(albumId).enqueue(object : Callback<List<UserAlbum>> {
            override fun onFailure(call: Call<List<UserAlbum>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<UserAlbum>>, response: Response<List<UserAlbum>>) {
                response.body()?.let {
                    userAlbumList?.value = it
                }
            }
        })
    }

    fun fetchAlbumDetail(albumId: Int, userId: Int) {
        MyApi().getUserAlbumByAlbumIdAndUserId(albumId, userId).enqueue(object : Callback<List<UserAlbum>> {
            override fun onFailure(call: Call<List<UserAlbum>>, t: Throwable) {
                userAlbumListByUserId?.value = null
            }

            override fun onResponse(call: Call<List<UserAlbum>>, response: Response<List<UserAlbum>>) {
                response.body()?.let {
                    userAlbumListByUserId?.value = it
                }
            }
        })
    }
}


