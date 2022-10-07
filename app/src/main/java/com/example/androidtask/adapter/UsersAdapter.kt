package com.example.androidtask.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.androidtask.R
import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.model.ResponseUsers
import com.example.androidtask.users_list.UsersListActivity

class UsersAdapter(var usersListActivity: UsersListActivity,var usersList: List<ResponseUsers?>,var postsList: List<ResponsePosts?>):
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>()  {

//    private val usersListActivity: UsersListActivity
//    private var usersList: MutableList<ResponseUsers>




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = usersList[position]
        holder.tvUsername.setText(user?.name)
        var countPosts = 0;
        postsList.forEach {
            if(it?.userId == user?.userId)
                countPosts++
        }
        holder.tvPost.setText(countPosts.toString())

        // loading album cover using Glide library
        Glide.with(usersListActivity)
            .load(user?.thumbnailUrl)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }
            })
            .apply(
                RequestOptions().placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
            )
            .into(holder.ivUserThumb)

        holder.itemView.setOnClickListener { usersListActivity.onMovieItemClick(user!!,postsList!!) }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    init {
        this.usersListActivity = this!!.usersListActivity
        this.usersList = this!!.usersList
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView
        var tvPost: TextView
        var ivUserThumb: ImageView
        var pbLoadImage: ProgressBar

        init {
            tvUsername = itemView.findViewById(R.id.tv_username)
            tvPost = itemView.findViewById(R.id.tv_post)
            ivUserThumb = itemView.findViewById(R.id.iv_user_thumb)
            pbLoadImage = itemView.findViewById(R.id.pb_load_image)
        }
    }

}

