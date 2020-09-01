package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//API key
//4b7ad36f69f80aa34703d042a53836e4

//TODO: Before modifying my code please merge it into yours or create a branch from it.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //switches you to the sign up layout
        textView_signUp.setOnClickListener {
        val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        //opens the activity that shows you the APP
       button_Preview.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewMovies::class.java)
            startActivity(intent)
        }
        //Write your network code here
        //Write your network code here
        //Write your network code here


    }
}