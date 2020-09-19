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
class RecyclerViewMovies : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)
        var page = 1
        //MVVM PART START HERE
        mainViewModel.movieLiveData
            .observe(this, {
                //button_Preview.isEnabled = true
                bindMoviesDataWithAdapter(it)
            })

        mainViewModel.onError.observe(this, {
            handleMovieError(it)
        })

        mainViewModel.loadMovieData(myPage = page)


        if(isRecyclerScrollable(main_recycler) == false) {
            page++
            mainViewModel.loadMovieData(myPage = page)
            //MVVM PART ENDS HERE
        }


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
    }

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(this@RecyclerViewMovies, errorMsg, Toast.LENGTH_LONG).show()
    }
    fun isRecyclerScrollable(recyclerView: RecyclerView): Boolean {
        return recyclerView.computeHorizontalScrollRange() > recyclerView.width || recyclerView.computeVerticalScrollRange() > recyclerView.height
    }

}