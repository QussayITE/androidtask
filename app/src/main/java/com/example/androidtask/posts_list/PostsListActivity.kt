package com.example.androidtask.posts_list

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidtask.R
import com.example.androidtask.adapter.PostsAdapter
import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers
import com.example.androidtask.utils.Constants.POSTS
import com.example.androidtask.utils.Constants.USER


class PostsListActivity : AppCompatActivity() {

    private var postsListPresenter: PostsListPresenter? = null
    private var rvPostsList: RecyclerView? = null
    private var ivUserImage: ImageView? = null
    private var user: ResponseUsers? = null
    private var postsList: List<ResponsePosts>? = null
    private var postsListToAdapter: MutableList<ResponsePosts>? = null
    private var postsAdapter: PostsAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_list)

        //get data from previous activity
        val bundle = getIntent().extras
        postsList  = bundle?.getParcelableArrayList<ResponsePosts>(POSTS)!!
        user  = bundle?.get(USER) as ResponseUsers?


        initUI()
    }

    private fun initUI() {
        rvPostsList = findViewById(R.id.rv_posts_list)
        ivUserImage = findViewById(R.id.iv_user_image)
        postsListToAdapter = ArrayList()



        // loading album cover using Glide library
        Glide.with(this)
            .load(user?.thumbnailUrl)
                        .apply(
                RequestOptions().placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
            )
            .into(ivUserImage!!);


        //get list of posts for user
        postsList?.forEach {
            if(it.userId == user?.userId)
                postsListToAdapter?.add(it)
        }

        //set recycleView with itwms
        postsAdapter = PostsAdapter(this, postsListToAdapter!!)
         mLayoutManager = LinearLayoutManager(this)

        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPostsList?.layoutManager = mLayoutManager
        rvPostsList?.hasFixedSize()
        postsAdapter =
            PostsAdapter(
                this,
                postsListToAdapter!!,
            )
        rvPostsList?.adapter = postsAdapter

    }
}