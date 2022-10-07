package com.example.androidtask.posts_list

import com.example.androidtask.users_list.UsersListView

class PostsListPresenter(var usersListView: PostsListView.View?) : PostsListView.Presenter {
    override fun onDestroy() {

    }
}