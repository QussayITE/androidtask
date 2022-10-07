package com.example.androidtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.R
import com.example.androidtask.model.ResponsePosts
import com.example.androidtask.posts_list.PostsListActivity

class PostsAdapter(var postsListActivity: PostsListActivity, var postsList: List<ResponsePosts?>):
    RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvBody: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvBody = itemView.findViewById(R.id.tv_body)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postsList[position]
        holder.tvTitle.setText(post?.title)
        holder.tvBody.setText(post?.body)

    }

    override fun getItemCount(): Int {
        return postsList.size

    }
}