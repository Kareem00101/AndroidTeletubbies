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
class MovieDetailsActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        //The two lines are below are for movie description in the second screen.
        val movieOverview: String? = intent.extras?.getString("movie_description")
        movieOverviewText.text = Html.fromHtml("$movieOverview")

    }
}

