package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recyclerview_movies.*
import kotlinx.android.synthetic.main.item_note.*

//Networks
//DEADLINE: Wednesday: 6PM.
//TODO: Create MovieRepository class                                                     //Done.
//TODO: try binding data to the item_note class without the images.                     //Done.
//TODO: find the way to pass images, first in a simple test then to the adapter.       //Not Done.
//TODO: for simple tests try binding data directly to the item_note layout.           //Not Done.
//TODO: after successful simple tests try linking data to the adapter.               //Done for MovieDetails.
//TODO: create adapter class.                                                       //Done.
//TODO: Link the recyclerView adapter to the network.                              //Done for MovieDetails.
//Successful Test? -> Congratulations!
//Design
//TODO: plus point Movie Details Layout and the required code.      //if finished before deadline with 12 hours.



class MainActivity : AppCompatActivity(){
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

    }

}