package com.example.teletubbies_task_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.android.synthetic.main.movie_details.*

class MovieDetailsActivity : AppCompatActivity(), MovieRepository.MovieCallBack  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        requestMoviesData("English")

    }
    private fun requestMoviesData(movieLang: String = "English")
    {

        MovieRepository.requestMovieData(movieLang, this)
    }
    private fun bindMoviesData(movie: MovieResponse)
    {
        movieOverviewText.text=movie.resultsList[0].description
    }

    override fun onMovieReady(movie: MovieResponse) {
        bindMoviesData(movie)
    }

    override fun onMovieError(errorMsg: String) {
        Toast.makeText(this@MovieDetailsActivity, errorMsg, Toast.LENGTH_LONG).show()
    }
}