package com.example.androidtask.users_list

import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers

interface UserItemClickListener {
     fun onMovieItemClick(user: ResponseUsers, posts: List<ResponsePosts?>)
}