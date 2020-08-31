package com.example.teletubbies_task_4

import com.google.gson.annotations.SerializedName

//https://api.themoviedb.org/3/movie/popular?api_key=4b7ad36f69f80aa34703d042a53836e4&language=en-US&page=1
//&language=en-US&page=1  this part is optional


data class MovieResponse(
    @SerializedName("result")
    val resultsList: List<MovieDetails>
)