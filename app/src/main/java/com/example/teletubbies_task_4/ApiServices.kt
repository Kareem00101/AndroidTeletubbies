package com.example.teletubbies_task_4

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("popular")
    fun getMovie(
        @Query("api key") apiKey: String,
        /*@Query("language") language: String,         //These 2 are optional.
        @Query("page") page: Int*/
    )
}

//https://api.themoviedb.org/3/movie/popular?api_key=4b7ad36f69f80aa34703d042a53836e4&language=en-US&page=1