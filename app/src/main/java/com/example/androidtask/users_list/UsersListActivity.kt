package com.example.androidtask.users_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.R
import com.example.androidtask.adapter.UsersAdapter
import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers
import com.example.androidtask.posts_list.PostsListActivity
import com.example.androidtask.utils.Constants.POSTS
import com.example.androidtask.utils.Constants.USER


class UsersListActivity : AppCompatActivity(), UsersListView.View, UserItemClickListener{

    private var usersListPresenter: UsersListPresenter? = null
    private var rvUserList: RecyclerView? = null
    private var usersList: List<ResponseUsers>? = null
    private var postsList: List<ResponsePosts>? = null
    private var usersAdapter: UsersAdapter? = null
    private var pbLoading: ProgressBar? = null
    private var mLayoutManager: LinearLayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)


        initUI()

        //Initializing presenter
        usersListPresenter = UsersListPresenter(this)
        usersListPresenter!!.requestDataFromServer()

    }

    /**
     * This method will initialize the UI components
     */
    private fun initUI() {
        rvUserList = findViewById(R.id.rv_users_list)
        usersList = ArrayList()
        postsList = ArrayList()
        usersAdapter = UsersAdapter(this, usersList!!, postsList!!)
        mLayoutManager = LinearLayoutManager(this)
        pbLoading = findViewById(R.id.pb_loading)

    }

    override fun showProgress() {
        pbLoading!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbLoading!!.visibility = View.GONE
    }

    override fun setDataToRecyclerView(usersArrayList: List<ResponseUsers?>?, postsArrayList: List<ResponsePosts?>?) {
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvUserList?.layoutManager = mLayoutManager
        rvUserList?.hasFixedSize()
        usersAdapter =
            UsersAdapter(
                this,
                usersArrayList!!,
                postsArrayList!!
            )
        rvUserList?.adapter = usersAdapter

    }

    override fun onResponseFailure(throwable: Throwable?) {
        Log.e("TAG", throwable?.message.toString());
        Toast.makeText(this, resources.getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    override fun onMovieItemClick(user: ResponseUsers, posts: List<ResponsePosts?>) {
        val detailIntent = Intent(this, PostsListActivity::class.java)
        detailIntent.putExtra(USER, user)
        detailIntent.putParcelableArrayListExtra(POSTS, ArrayList(posts))
        startActivity(detailIntent)
    }
}
