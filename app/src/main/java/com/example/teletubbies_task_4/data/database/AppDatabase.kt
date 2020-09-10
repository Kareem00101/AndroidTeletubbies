package com.example.teletubbies_task_4.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teletubbies_task_4.data.ui.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase()
{
    abstract fun getMovieDao(): MovieDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            if (INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "movie_db"
            ).allowMainThreadQueries().build()

            return INSTANCE!!
        }
    }
}