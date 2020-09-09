package com.example.teletubbies_task_4.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.teletubbies_task_4.R
import kotlinx.android.synthetic.main.movie_details.*

//TODO: always getting same movie data, on every movie.
//TODO: fix this error.
class MovieDetailsActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        //The two lines are below are for movie description in the second screen.
        val movieOverview: String? = intent.extras?.getString("movie_description")
        movieOverviewText.text = Html.fromHtml("$movieOverview")
        val movieTitle : String? = intent.extras?.getString("title")
        movieOverviewTitle.text= Html.fromHtml("$movieTitle")

    }
}

