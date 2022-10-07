package com.example.androidtask.network

import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    fun getUsers(
    ): Call<List<ResponseUsers?>>?

    @GET("posts")
    fun getPosts(
    ): Call<List<ResponsePosts?>>?

}