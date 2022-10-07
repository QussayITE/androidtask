package com.example.androidtask.model

data class ResponseUserPosts(val user: ResponseUsers, val posts: List<ResponsePosts>) {
}