package com.example.teletubbies_task_4.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.example.teletubbies_task_4.R
import kotlinx.android.synthetic.main.movie_details.*
import com.example.teletubbies_task_4.UI.RecyclerViewMovies
import com.example.teletubbies_task_4.data.Repository.MovieRepository
import com.example.teletubbies_task_4.data.ui.Movie


class MovieDetailsActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        //The lines are below are for the movie description & name in the second screen.
        val movieOverview: String? = intent.extras?.getString("movie_description")
        movieOverviewText.text = Html.fromHtml("$movieOverview")
        val movieTitle : String? = intent.extras?.getString("title")
        movieOverviewTitle.text= Html.fromHtml("$movieTitle")

        //For FavoriteList, getting the ID inorder to check the item i need.
        val myMovieID: Long? = intent.extras?.getLong("movieID")

        //In order to add the movie to the favorite list.
        favorite_button.setOnClickListener {
            if (myMovieID != null) {
                MovieRepository.requestForFavorite(myMovieID = myMovieID, Add = true)
            }
        }
        //In order to remove the movie from the favorite list.
        RemoveFromFavorite.setOnClickListener {
            if (myMovieID != null) {
                MovieRepository.requestForFavorite(myMovieID = myMovieID, Add = false)
            }
        }

    }
}

