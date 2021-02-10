package com.example.rxmvp.io

import com.example.rxmvp.MyApi
import com.example.rxmvp.Post
import com.example.rxmvp.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostRepositoryImp(val compositeDisposable: CompositeDisposable) : PostRepository {
    private val api: MyApi = RetrofitClient.retrofit.create(MyApi::class.java)

    override fun getPosts(onGetPostSuccess: (post: List<Post>) -> Unit, onGetPostsError: (errorMessage: String) -> Unit) {
        compositeDisposable.add(
                api.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    //Success
                                    onGetPostSuccess(it)
                                },
                                {
                                    //Error
                                    it.message?.let {
                                        //El let es un especie de IF it.message <> null, entonces onGetPostsError()
                                        onGetPostsError(it)
                                    } ?: run {
                                        //El run es un IF it.message = null, entonces muestro mensaje default
                                        onGetPostsError("Error desconocido al obtener los posts")
                                    }
                                })
        )
    }
}