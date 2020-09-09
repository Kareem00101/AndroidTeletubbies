package com.example.teletubbies_task_4.data.ui

data class Movie (
    val title: String,
    val posterPortrait: String,
    val rating: Double,
    val language: String,
    val release: String,

    val description: String = " ",

/*extras
val pictureLandsacape: String,
val votes: Int,
val adult: Boolean
 */

)