package com.example.teletubbies_task_4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

        @GET("Enter URL Path")
        fun getMovie(
            @Query("Enter Query") movieName: String

        ): Call<MovieResponse>  //not yet implemented
                                // but make sure to match names
                                // with the response class
    }
