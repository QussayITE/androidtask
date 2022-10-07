package com.example.androidtask.users_list

import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers

class UsersListPresenter(var usersListView: UsersListView.View?) : UsersListView.Presenter,
    UsersListView.Model.OnFinishedListener {

  //  private var usersListView: UsersListView.View? = null

    private var usersListModel: UsersListView.Model? = null

    override fun onDestroy() {
        this.usersListView = null
    }

    override fun requestDataFromServer() {
        usersListModel = UsersListModel()
        if (usersListView != null) {
            usersListView!!.showProgress();
        }
        usersListModel!!.getUsersList(this)
    }

    override fun onFinished(usersArrayList: List<ResponseUsers?>?, postsArrayList: List<ResponsePosts?>?) {
        usersListView!!.setDataToRecyclerView(usersArrayList, postsArrayList);
        if (usersListView != null) {
            usersListView!!.hideProgress();
        }
    }

    override fun onFailure(t: Throwable?) {
        usersListView!!.onResponseFailure(t);
        if (usersListView != null) {
            usersListView!!.hideProgress();
        }
    }
}