package com.example.rxmvp.view

import com.example.rxmvp.Post

interface PostView {
    fun showLoader()
    fun hideLoader()
    fun showError(message: String)
    fun updatePosts(posts: List<Post>)
}