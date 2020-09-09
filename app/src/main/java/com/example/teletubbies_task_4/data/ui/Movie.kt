package com.example.teletubbies_task_4.data.ui

//Insert dependencies then remove the // in the comments below


//@Entity(tableName = "Movie_table")
data class Movie (
    //@PrimaryKey
    //val id: Long,
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