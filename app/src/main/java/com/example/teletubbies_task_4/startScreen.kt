package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start_screen.*

class startScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
       imageView3.alpha= 0f
        imageView3.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent (this@startScreen, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        textView5.alpha= 0f
        textView5.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent (this@startScreen, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        textView6.alpha= 0f
        textView6.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent (this@startScreen, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}