package com.example.androidtask.users_list

import android.util.Log
import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers
import com.example.androidtask.network.ApiClient
import com.example.androidtask.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersListModel : UsersListView.Model {
    override fun getUsersList(onFinishedListener: UsersListView.Model.OnFinishedListener?) {
        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)


        //call one api
        val callGetUsers: Call<List<ResponseUsers?>>? = apiService.getUsers()
        val call2GetPosts: Call<List<ResponsePosts?>>? = apiService.getPosts()

        var isCompleteUsers : Boolean = false
        var isCompletePosts : Boolean = false
        var usersList : List<ResponseUsers?>? = null
        var postsList : List<ResponsePosts?>? = null

        call2GetPosts!!.enqueue(object : Callback<List<ResponsePosts?>?> {

            override fun onFailure(call: Call<List<ResponsePosts?>?>, t: Throwable) {
                // Log error here since request failed
                Log.e("TAG", t.toString())
                onFinishedListener!!.onFailure(t)
            }

            override fun onResponse(
                call: Call<List<ResponsePosts?>?>,
                response: Response<List<ResponsePosts?>?>
            ) {
                val posts: List<ResponsePosts?>? = response.body()
                Log.d("TAG", "Number of posts received: " + posts?.size)
                //onFinishedListener!!.onFinished(users)
                isCompletePosts = true
                postsList = response.body()

                if(isCompletePosts && isCompleteUsers) {
                    onFinishedListener!!.onFinished(usersList, postsList)
                }
            }
        })

        callGetUsers!!.enqueue(object : Callback<List<ResponseUsers?>?> {

            override fun onFailure(call: Call<List<ResponseUsers?>?>, t: Throwable) {
                // Log error here since request failed
                Log.e("TAG", t.toString())
                onFinishedListener!!.onFailure(t)
            }

            override fun onResponse(
                call: Call<List<ResponseUsers?>?>,
                response: Response<List<ResponseUsers?>?>
            ) {
                val users: List<ResponseUsers?>? = response.body()
                Log.d("TAG", "Number of users received: " + users?.size)
             //   onFinishedListener!!.onFinished(users)
                isCompleteUsers = true
                usersList = response.body()
                if(isCompletePosts && isCompleteUsers) {
                    onFinishedListener!!.onFinished(usersList, postsList)
                }
            }
        })

    }

}