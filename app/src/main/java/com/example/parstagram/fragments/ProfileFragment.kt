package com.example.parstagram.fragments

import android.util.Log
import com.example.parstagram.Post
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : HomeFragment() {

    override fun queryPosts() {

        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")

        query.findInBackground { posts, e ->
            if (e != null) {
                Log.e(TAG, "Error fetching posts")
            } else {
                if (posts != null) {
                    for (post in posts) {
                        Log.i(
                            TAG, "Post: " + post.getDescription() + " , username: " +
                                    post.getUser()?.username)
                    }
                    allPosts.addAll(posts)
                    adapter.notifyDataSetChanged()
                }
            }
        }

    }

}