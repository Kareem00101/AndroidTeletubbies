package com.example.teletubbies_task_4.data.database

import androidx.room.*
import com.example.teletubbies_task_4.data.ui.Movie
import com.example.teletubbies_task_4.data.ui.MovieRated

@Dao
interface MovieRatedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movie: List<MovieRated>)

    @Query("SELECT * FROM MovieRated_table")
    fun getAllMovies(): List<MovieRated>

    //For favorite list, you can find the modification of isFavorite boolean in the repository file.
    @Query("SELECT * FROM MovieRated_table where isFavorite=1")
    fun getFavoriteTopRatedMovies(): List<MovieRated>

    @Delete
    fun deleteMovie(movie: MovieRated)

    @Query("DELETE FROM MovieRated_table")
    fun deleteAll()
}