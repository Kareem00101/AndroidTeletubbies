package com.example.teletubbies_task_4.data.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.teletubbies_task_4.data.ui.Movie

//add dependency then remove // from the comment and check the interface with class Movie.kt

@Dao
interface MovieDao {
    @Insert
    fun addMovies(movie: List<Movie>)

    //@Query("SELECT * FROM MOVIE")
    fun getAllForecasts(): List<Movie>

    //@Delete
    fun deleteForecast(movie: Movie)

    //@Query("DELETE FROM Movie")
    fun deleteAll()
}