package com.example.rxmvp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rxmvp.*
import com.example.rxmvp.io.PostRepositoryImp
import com.example.rxmvp.presenter.PostPresenterImp
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PostView {
    val compositeDisposable = CompositeDisposable()
    private lateinit var adapter: PostsAdapter
    private lateinit var rvPosts: RecyclerView
    private val presenter = PostPresenterImp(      //<-Instancia de presenter
            this,                            //<-Instancia de view
            PostRepositoryImp(compositeDisposable) //<-Instancia de model
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyclerViewPosts()
        //Gatillamos el proceso en presenter
        presenter.doGetPosts()
    }

    private fun initializeRecyclerViewPosts() {
        rvPosts = findViewById(R.id.rvPosts)
        adapter = PostsAdapter()
        rvPosts.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun updatePosts(posts: List<Post>) {
        adapter.posts = posts
        adapter.notifyDataSetChanged()
    }
}
