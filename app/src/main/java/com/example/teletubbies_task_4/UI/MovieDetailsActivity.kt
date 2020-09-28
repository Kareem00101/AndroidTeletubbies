package com.example.teletubbies_task_4.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.example.teletubbies_task_4.R
import kotlinx.android.synthetic.main.movie_details.*
import com.example.teletubbies_task_4.UI.RecyclerViewMovies


class MovieDetailsActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        //The lines are below are for the movie description & name in the second screen.
        val movieOverview: String? = intent.extras?.getString("movie_description")
        movieOverviewText.text = Html.fromHtml("$movieOverview")
        val movieTitle : String? = intent.extras?.getString("title")
        movieOverviewTitle.text= Html.fromHtml("$movieTitle")

        favorite_button.setOnClickListener {
            val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        }


    }
}

