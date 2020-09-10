package com.example.teletubbies_task_4.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teletubbies_task_4.R
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Sign in button in sign up switches you back to sign in layout.
        Sign_In.setOnClickListener {
            val intent = Intent (this@MainActivity2, MainActivity::class.java)
            startActivity(intent)
        }
    }
}