package com.example.teletubbies_task_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recyclerview_movies.*
import kotlinx.android.synthetic.main.item_note.*

//This is the activity that contains the recycler view.
class RecyclerViewMovies : AppCompatActivity(), MovieRepository.MovieCallBack  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)

        requestMoviesData("English")


    }
    private fun requestMoviesData(movieLang: String = "English")
    {

        MovieRepository.requestMovieData(movieLang, this)
    }

    //Binding data function, necessary for simple test only use this function if you change
    //setContentView(R.layout.item_movies)
    private fun bindMoviesData(movie: MovieResponse)
    {
        titleOfMovie.text = movie.resultsList[0].title
        releaseDate.text = movie.resultsList[0].release
        ratingOfMovie.text = movie.resultsList[0].rating.toString()
        languageOfMovie.text = movie.resultsList[0].language

    }

    //This functions links data source with the adapter.
    private fun bindMoviesDataWithAdapter(movie: MovieResponse)
    {
        val displayList = ArrayList<MovieDetails>()
        for(b in movie.resultsList)
        {
            displayList.add(b)
        }

        //Designing recycler view and linking it to the adapter.
        main_recycler.layoutManager = LinearLayoutManager(this@RecyclerViewMovies, LinearLayoutManager.VERTICAL, false )
        main_recycler.adapter = MovieAdapter(displayList)
    }


    //MovieCallBack members.
    override fun onMovieReady(movie: MovieResponse) {
        //calling data binding function.
        bindMoviesDataWithAdapter(movie)
    }
    //In case of an error.
    override fun onMovieError(errorMsg: String) {

        Toast.makeText(this@RecyclerViewMovies, errorMsg, Toast.LENGTH_LONG).show()
    }

}