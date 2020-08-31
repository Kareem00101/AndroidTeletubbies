package com.example.teletubbies_task_4

import com.google.gson.annotations.SerializedName

//https://api.themoviedb.org/3/movie/popular?api_key=4b7ad36f69f80aa34703d042a53836e4&language=en-US&page=1
//&language=en-US&page=1  this part is optional


data class MovieResponse(

    @SerializedName("results")
    val resultsList: List<MovieDetails>

)


data class MovieDetails(
//Main Data
    @SerializedName("title")               //Movie Name
    val title: String,
    @SerializedName("poster_path")         //Movie Picture in screen1
    val posterPortrait: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("original_language")   //we should add a Language: next to it
    val language: String,
    @SerializedName("release_date")
    val release: String,

//Data for the bonus

    @SerializedName("backdrop_path")       //Movie Picture in screen 2 after selecting Movie to view description
    val pictureLandscape: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("vote_count")
    val votes: Int,
    @SerializedName("adult")               //We can make a message or an adult only icon to appear when true
    val adult: Boolean

)

// This class is for the configuration API which I added so that the poster and backdrop paths are cpmplete.

data class ImageDetails(
    @SerializedName("secure_base_url")
    val baseURL: String,
    @SerializedName("backdrop_sizes")
    val backdropSize: List<String>,
    @SerializedName("poster_sizes")
    val posterSize: List<String>

)

/*To whoever is making the main:

In order to generate a fully working image URL, you'll need 3 pieces of data.
Those pieces are a base_url, a file_size and a file_path.
The first two pieces can be retrieved by calling the  API and the third is the file path you're wishing to grab on a particular media object.
 Here's what a full image URL looks like if the poster_path of /kqjL17yufvn9OVLyXYpvtyrFfak.jpg was returned for a movie, and you were looking for the w500 size:

https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

*/