package com.example.androidtask.posts_list

import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers

class PostsListView {

    interface View {
        fun setDataToRecyclerView(user: ResponseUsers, postsArrayList: List<ResponsePosts?>?)
    }

    interface Presenter {
        fun onDestroy()
    }
}