package com.example.teletubbies_task_4

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var instance: Retrofit? = null


    fun getClient(): Retrofit {
        if (instance == null)
            // TODO: Make sure to Enter Enter Your API's URL
            instance = Retrofit.Builder()
                .baseUrl("Enter Your API's URL Here")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient().newBuilder().build())
                .build()

        return instance!!
    }
}