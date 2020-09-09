package com.example.teletubbies_task_4.data

import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie

class MovieMapper {

        fun mapToMovieUi(movieResponse: MovieResponse): List<Movie> {
        val Movies = mutableListOf<Movie>()
            var title = ""
            var posterPortrait = ""
            var rating = ""
            var language = ""
            var release =""
            var description = ""

        movieResponse.resultsList.forEach {

            Movies.add(Movie(it.title, it.posterPortrait, it.rating, it.language,it.release, it.description))
        }
        return Movies
    }
}
