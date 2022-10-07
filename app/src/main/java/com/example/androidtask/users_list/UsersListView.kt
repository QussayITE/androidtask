package com.example.androidtask.users_list

import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers

class UsersListView {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(usersArrayList: List<ResponseUsers?>?, postsArrayList: List<ResponsePosts?>?)
            fun onFailure(t: Throwable?)
        }

        fun getUsersList(onFinishedListener: OnFinishedListener?, )
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(userArrayList: List<ResponseUsers?>?, postsArrayList: List<ResponsePosts?>?)
        fun onResponseFailure(throwable: Throwable?)
    }

    interface Presenter {
        fun onDestroy()
        fun requestDataFromServer()
    }

}