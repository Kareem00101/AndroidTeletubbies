package com.example.teletubbies_task_4.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.teletubbies_task_4.data.ui.Movie

//TODO: Implement this class.
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase()
{
    //Implement abstract function.
    //Instance creation implementation.
    //Remember Threading.
    //Create companion object for the abstract class.
    //The database is the one who implements the abstract functions.

}