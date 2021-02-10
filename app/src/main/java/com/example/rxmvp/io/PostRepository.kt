package com.example.rxmvp.io

import com.example.rxmvp.Post

interface PostRepository {
    fun getPosts(
            //Recibe por parámetro la implementación de 2 métodos
            //Uno recibe por parámetros un listado de Post y otro un mensaje string
            //Los escribimos resumidas en lambda
            onGetPostSuccess: (post: List<Post>) -> Unit,
            onGetPostsError: (errorMessage: String) -> Unit
    )
}