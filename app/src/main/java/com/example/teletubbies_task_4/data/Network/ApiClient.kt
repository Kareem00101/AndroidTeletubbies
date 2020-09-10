package com.example.teletubbies_task_4.data.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Base URL for poster used in the adapter to link the images to the adapter.
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