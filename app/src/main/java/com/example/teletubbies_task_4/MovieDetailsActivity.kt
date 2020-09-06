package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.android.synthetic.main.movie_details.*

//TODO: always getting same movie data, on every movie.
//TODO: fix this error.
class MovieDetailsActivity() : AppCompatActivity(), MovieRepository.MovieCallBack  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        val movieOverview : String? = intent.extras?.getString("movie_description")
        movieOverviewText.text = Html.fromHtml("<b>MovieDescription:</b>$movieOverview")
       // requestMoviesData("English")
        //position++

    }
    var position = 1

    private fun requestMoviesData(movieLang: String = "English")
    {

        MovieRepository.requestMovieData(movieLang, this)
    }
    //this method gives same data for all movies.
    private fun bindMoviesData(movie: MovieResponse)
    {
        movieOverviewText.text=movie.resultsList[0].description
    }
    //How would i use list to link data depending on the position?
    private fun bindMoviesDataWithVariety(movie: MovieResponse) {
        val displayList = ArrayList<MovieDetails>()
        for (b in movie.resultsList) {
            displayList.add(b)
        }

    }

    //takes position so it combines text with the suitable data.
    private fun connectMovieData(movie: MovieResponse)
    {
        movieOverviewText.text = movie.resultsList[position].description
    }

    //Trying to get position so it changes with movie.
    override fun onMovieReady(movie: MovieResponse) {
        connectMovieData(movie)

    }
    //in case of an error.
    override fun onMovieError(errorMsg: String) {
        Toast.makeText(this@MovieDetailsActivity, errorMsg, Toast.LENGTH_LONG).show()
    }
}