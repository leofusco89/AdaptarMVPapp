package com.example.rxmvp.presenter

import com.example.rxmvp.io.PostRepository
import com.example.rxmvp.view.PostView

class PostPresenterImp(val view: PostView, val model: PostRepository) : PostPresenter {
    override fun doGetPosts() {
        view.showLoader()
        model.getPosts(
                //getPosts recibe por parámetro la implementación 2 métodos
                //El primero recibe un listado de Post y el segundo un mensaje string de error
                //Los escribimos resumidas en lambda
                { posts ->
                    view.updatePosts(posts)
                    view.hideLoader()

                },
                { message ->
                    view.hideLoader()
                    view.showError(message)
                }
        )
    }
}