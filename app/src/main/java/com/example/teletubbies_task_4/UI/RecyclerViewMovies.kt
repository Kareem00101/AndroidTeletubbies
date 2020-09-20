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

    //A var of adapter for functions accessibility
    private lateinit var RvAdapter: MovieAdapter
    private val mainViewModel: MainViewModel by viewModels()
    //Page variable for pagination
    var page = 1
    //Pagination bug fix trial
    var isPagination = false

    val linearLayoutManager = LinearLayoutManager(this@RecyclerViewMovies, LinearLayoutManager.VERTICAL, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)

        //MVVM PART START HERE
        mainViewModel.movieLiveData.observe(this, {
            //checks if this is first load or a new page.
            if (isPagination) {
                linearLayoutManager.stackFromEnd
                RvAdapter.updateData(it)
            //if this is first load it will set up the recycler.
            }else {
                setupRecycler(it)
            }
        })


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
                    //this means we reached the scroll limit, therefore we increment the page,
                    //in order to load the next page of the code.
                    page++
                    //setting pagination to true so that movieLiveData updates instead of recreating.
                    isPagination = true
                    //calling load function to load the data of the next page.
                    mainViewModel.loadMovieData(myPage = page)

                }
            }
        })

    }

    //Pagination bug fix test method.
    private fun setupRecycler(movie: List<Movie>) {

        main_recycler.layoutManager = linearLayoutManager
        RvAdapter = MovieAdapter(movie)
        main_recycler.adapter = RvAdapter
    }

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(this@RecyclerViewMovies, errorMsg, Toast.LENGTH_LONG).show()
    }

}