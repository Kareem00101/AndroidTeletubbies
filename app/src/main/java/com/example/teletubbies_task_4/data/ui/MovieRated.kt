package com.example.teletubbies_task_4.data.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieRated_table")
data class MovieRated (
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
    val description: String = " ",

    //Not Used Yet.
    /*extras
    val pictureLandscape: String,
    val votes: Int,
    val adult: Boolean
    */

    //For favorite list
    var isFavorite: Boolean = false

)
