package com.example.teletubbies_task_4.data.database

import androidx.room.*
import com.example.teletubbies_task_4.data.ui.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movie: List<Movie>)


    @Query("SELECT * FROM Movie_table")
    fun getAllMovies(): List<Movie>


    //For favorite list.
    @Query("SELECT * FROM Movie_table where isFavorite = 1")
    fun getFavoriteMovies(): List<Movie>


    @Delete
    fun deleteMovie(movie: Movie)

    @Query("DELETE FROM Movie_table")
    fun deleteAll()
}