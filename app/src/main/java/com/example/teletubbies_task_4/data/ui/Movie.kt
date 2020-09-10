package com.example.teletubbies_task_4.data.ui

//imports
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO: Check how will we be using the id.
@Entity(tableName = "Movie_table")
data class Movie (
    //We have an ID from our movie response.
    @PrimaryKey
    val id: Long,

    //Used in the RecyclerView Screen.
    val title: String,
    val posterPortrait: String,
    val rating: Double,
    val language: String,
    val release: String,
    //Used in the MovieOverview Screen.
    val description: String = " "

    //Not Used Yet.
    /*extras
    val pictureLandscape: String,
    val votes: Int,
    val adult: Boolean
    */
)