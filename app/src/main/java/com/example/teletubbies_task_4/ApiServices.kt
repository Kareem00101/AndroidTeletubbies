package com.example.teletubbies_task_4

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String,
        /*@Query("language") language: String,         //These 2 are optional.
        @Query("page") page: Int*/
    )

    @GET("/configuration")
    fun config (
        @Query("api_key") apiKey: String,
    )
}

//Popular Movie
//https://api.themoviedb.org/3/movie/popular?api_key=4b7ad36f69f80aa34703d042a53836e4&language=en-US&page=1

//Configuration fot the image path
//https://api.themoviedb.org/3/configuration?api_key=4b7ad36f69f80aa34703d042a53836e4
//4b7ad36f69f80aa34703d042a53836e4