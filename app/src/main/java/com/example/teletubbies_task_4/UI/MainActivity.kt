package com.example.teletubbies_task_4.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teletubbies_task_4.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //switches you to the sign up layout
        Sign_Up.setOnClickListener {
        val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        //opens the activity that shows you the APP
       Preview_App.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewMovies::class.java)
            startActivity(intent)
        }

    }

}