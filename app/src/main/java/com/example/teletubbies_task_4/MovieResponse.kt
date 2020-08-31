package com.example.teletubbies_task_4

import com.google.gson.annotations.SerializedName

//https://api.themoviedb.org/3/movie/popular?api_key=4b7ad36f69f80aa34703d042a53836e4&language=en-US&page=1
//&language=en-US&page=1  this part is optional


data class MovieResponse(

    @SerializedName("results")
    val resultsList: List<MovieDetails>

)


data class MovieDetails(

    @SerializedName("title")               //Movie Name
    val title: String,
    @SerializedName("poster_path")         //Movie Picture in screen1
    val posterPortrait: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("original_language")
    val language: String,
    @SerializedName("release_date")
    val release: String,



)