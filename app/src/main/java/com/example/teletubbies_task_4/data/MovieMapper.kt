package com.example.teletubbies_task_4.data

import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie
import java.util.*


class MovieMapper {
    //a linked list variable in order to save the old list and add the new after it.
    var joinedList = LinkedList<Movie>()
        fun mapToMovieUi(movieResponse: MovieResponse): List<Movie> {
        val Movies = mutableListOf<Movie>()
            var title = ""
            var posterPortrait = ""
            var rating = ""
            var language = ""
            var release =""
            var description = ""

        movieResponse.resultsList.forEach {

            Movies.add(Movie(it.id,it.title, it.posterPortrait, it.rating, it.language,it.release, it.description))
        }
            //adds the new list after the older version.
            joinedList.addAll(Movies)
            //returing the linked list for the whole code to deal with.
            return joinedList
    }
}
