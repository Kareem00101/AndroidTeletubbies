package com.example.teletubbies_task_4

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO:choose whether you want to use the configuration API or use the constant URL to get pictures


//TODO:If you choose the API method delete the 2 constants below
//TODO:If you choose the constant method add width after the w in the 2 URL below

const val Poster_Base_URL="https://image.tmdb.org/t/p/w"
const val Landscape_Base_URL="https://image.tmdb.org/t/p/w"

object ApiClient {
    private var instance: Retrofit? = null


    fun getClient(): Retrofit {
        if (instance == null)

            instance = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient().newBuilder().build())
                .build()

        return instance!!
    }
}