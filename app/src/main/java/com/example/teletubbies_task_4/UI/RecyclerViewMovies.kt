package com.example.teletubbies_task_4.UI

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teletubbies_task_4.R
import com.example.teletubbies_task_4.data.ui.Movie
import kotlinx.android.synthetic.main.activity_recyclerview_movies.*


//This is the activity that contains the recycler view.
class RecyclerViewMovies : AppCompatActivity(){

    //Pagination bug fix test variables.
    //private val items: ArrayList<Movie>? = null
    //private lateinit var RvAdapter: MovieAdapter
    private val mainViewModel: MainViewModel by viewModels()
    var page = 1
    //Pagination bug fix trial
    //var isPagination = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)


        //MVVM PART START HERE

        mainViewModel.movieLiveData
                .observe(this, {
                    //button_Preview.isEnabled = true
                    bindMoviesDataWithAdapter(it)
                })
        //Pagination bug fix test method.
        /*mainViewModel.movieLiveData.observe(this, {
            if (RvAdapter == null) {
                setupRecycler(it)
            } else {
                val itemCount: Int = RvAdapter.getItemCount()
                RvAdapter.updateData(it)
                RvAdapter.notifyItemRangeInserted(itemCount, it.size)
            }
        })*/


        mainViewModel.onError.observe(this, {
            handleMovieError(it)
        })

        mainViewModel.loadMovieData(myPage = page)
        //MVVM ends here

        //For pagination
        main_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    mainViewModel.loadMovieData(myPage = page)

                }
            }
        })

    }

    //This functions links data source with the adapter.
    private fun bindMoviesDataWithAdapter(movie: List<Movie>)
    {
        //Designing recycler view and linking it to the adapter.
        main_recycler.layoutManager = LinearLayoutManager(
            this@RecyclerViewMovies,
            LinearLayoutManager.VERTICAL, false
        )
        main_recycler.adapter = MovieAdapter(movie)
        //Pagination bug fix trial
        /*val linearLayoutManager = LinearLayoutManager(this@RecyclerViewMovies, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager.stackFromEnd = true
        main_recycler.layoutManager = linearLayoutManager
        //main_recycler.adapter = MovieAdapter(movie)
        var adapter = MovieAdapter(movie)
        main_recycler.adapter = adapter
        adapter.updateData(movie)
        main_recycler.scrollToPosition(adapter.getItemCount() - 1)*/

    }
    //Pagination bug fix test method.
    /*private fun setupRecycler(movie: List<Movie>) {
        main_recycler.layoutManager = LinearLayoutManager(
            this@RecyclerViewMovies,
            LinearLayoutManager.VERTICAL, false
        )
        RvAdapter = MovieAdapter(movie)
        main_recycler.adapter = RvAdapter
    }*/

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(this@RecyclerViewMovies, errorMsg, Toast.LENGTH_LONG).show()
    }
    fun isRecyclerScrollable(recyclerView: RecyclerView): Boolean {
        return recyclerView.computeHorizontalScrollRange() > recyclerView.width || recyclerView.computeVerticalScrollRange() > recyclerView.height
    }

    //Pagination bug fix interface trial.
    /*override fun updateData(movieList: List<Movie>) {
        items!!.clear()
        items!!.addAll(movieList)
    }*/

}