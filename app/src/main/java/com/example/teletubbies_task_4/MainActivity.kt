package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
textView_signUp.setOnClickListener {
   val intent = Intent(this@MainActivity, MainActivity2::class.java)
    startActivity(intent)
}
       button_Preview.setOnClickListener {
            val intent = Intent(this@MainActivity, recyclerview_movies::class.java)
            startActivity(intent)
        }
    }
}