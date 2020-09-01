package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_note.*

//API key
//4b7ad36f69f80aa34703d042a53836e4

//Networks
//DEADLINE: Wednesday: 6PM.
//TODO: Before modifying my code please merge it into yours or create a branch from it.  //@NardineYousry.
//TODO: Create MovieRepository class                                                     //Done.
//TODO: try binding data to the item_note class without the images.                     //
//TODO: find the way to pass images, first in a simple test then to the adapter.       //
//TODO: for simple tests try binding data directly to the item_note layout.           //
//TODO: after successful simple tests try linking data to the adapter.               //
//TODO: create adapter class.                                                       //
//TODO: Link the recyclerView adapter to the network.                              //
//TODO: Testing, don't use pixel 2 in testing it causes errors!                   //
//Successful Test? -> Congratulations!
//Design
//TODO: item_note class might need modifications.                    //Depending on network results.
//TODO: plus point Movie Details Layout and the required code.      //if finished before deadline with 12 hours.

class MainActivity : AppCompatActivity(), MovieRepository.MovieCallBack {
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

    private fun requestMoviesData(movieLang: String = "English")
    {
        //TODO: This tag
        MovieRepository.requestMovieData(movieLang, this)
    }

    //Binding data function, necessary for simple test.
    private fun bindMoviesData(movie: MovieResponse)
    {
        releaseDate.text = movie.resultsList[0].release
        ratingOfMovie.text = movie.resultsList[0].rating.toString()


    }




    //MovieCallBack members.
    override fun onMovieReady(movie: MovieResponse) {
        //calling data binding function.
        bindMoviesData(movie)
    }

    override fun onMovieError(errorMsg: String) {
        //TODO: check which activity ???
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }
}