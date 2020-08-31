package com.example.teletubbies_task_4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

        @GET("/movie/popular")        // if an error occurred the / at the beginning maybe the problem
        fun getMovie(

            @Query("api_key")apiKey: String,
                /*@Query("language") language: String,         //These 2 are optional.
                @Query("page") page: Int*/
        ): Call<MovieResponse>

    @GET("/configuration")          // if an error occurred the / at the beginning maybe the problem
    fun config (
        @Query("api_key") apiKey: String,
    ): Call<ImageDetails>

    }


//Popular Movie
//https://api.themoviedb.org/3/movie/popular?api_key=4b7ad36f69f80aa34703d042a53836e4&language=en-US&page=1

//Configuration fot the image path
//https://api.themoviedb.org/3/configuration?api_key=4b7ad36f69f80aa34703d042a53836e4

//API key
//4b7ad36f69f80aa34703d042a53836e4