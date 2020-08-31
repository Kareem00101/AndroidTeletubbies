package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signin.*

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
textView_signUp.setOnClickListener {
   val intent = Intent(this@Signin, SignUp::class.java)
    startActivity(intent)
}
    }
}