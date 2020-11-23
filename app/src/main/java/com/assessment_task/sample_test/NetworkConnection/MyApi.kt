package com.tm.repository

import com.tm.model.User
import com.tm.model.UserAlbum
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface MyApi {

    @GET("/users")
    fun getUsersList(): Call<List<User>>

    @GET("/photos")
    fun getUserAlbumListByUserId(@Query("albumId") albumId: Int?): Call<List<UserAlbum>>

    @GET("/photos")
    fun getUserAlbumByAlbumIdAndUserId(@Query("albumId") albumId: Int?, @Query("id") id: Int?): Call<List<UserAlbum>>


    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyApi::class.java)
        }
    }
}
