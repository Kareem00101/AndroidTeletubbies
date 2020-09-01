package com.example.teletubbies_task_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recyclerview_movies.*
import kotlinx.android.synthetic.main.item_note.*

//API key
//4b7ad36f69f80aa34703d042a53836e4

//Networks
//DEADLINE: Wednesday: 6PM.
//TODO: Before modifying my code please merge it into yours or create a branch from it.  //@NardineYousry.
//TODO: Create MovieRepository class                                                     //Done.
//TODO: try binding data to the item_note class without the images.                     //Done.
//TODO: find the way to pass images, first in a simple test then to the adapter.       //Not Done.
//TODO: for simple tests try binding data directly to the item_note layout.           //Not Done.
//TODO: after successful simple tests try linking data to the adapter.               //Done for MovieDetails.
//TODO: create adapter class.                                                       //Done.
//TODO: Link the recyclerView adapter to the network.                              //Done for MovieDetails.
//TODO: Testing, don't use pixel 2 in testing it causes errors!                   //Checked.
//Successful Test? -> Congratulations!
//Design
//TODO: item_note class might need modifications.                    //Depending on network results.
//TODO: plus point Movie Details Layout and the required code.      //if finished before deadline with 12 hours.

//DO NOT FORGET!
//TODO: I modified android:hint to tools:hint in item_note for testing purposes. //Modify in the end.
//TODO: I modified the manifest for testing purposes.              //Modify at end of tests.
//TODO: Remember to return to original state.                     //Complete!

class MainActivity : AppCompatActivity(), MovieRepository.MovieCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)

        //TODO: DO NOT DELETE THE CODE BELOW, IT IS COMMENTED FOR TEST PURPOSES.
        //switches you to the sign up layout
        /*textView_signUp.setOnClickListener {
        val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        //opens the activity that shows you the APP
       button_Preview.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewMovies::class.java)
            startActivity(intent)
        }*/
        //Write your network code here
        //Write your network code here
        //Write your network code here

        requestMoviesData("English")


    }

    private fun requestMoviesData(movieLang: String = "English")
    {
        //TODO: This tag
        MovieRepository.requestMovieData(movieLang, this)
    }

    //Binding data function, necessary for simple test only use this function if you change
    //setContentView(R.layout.item_movies)
    private fun bindMoviesData(movie: MovieResponse)
    {
        titleOfMovie.text = movie.resultsList[0].title
        releaseDate.text = movie.resultsList[0].release
        ratingOfMovie.text = movie.resultsList[0].rating.toString()
        languageOfMovie.text = movie.resultsList[0].language

    }

    private fun bindMoviesDataWithAdapter(movie: MovieResponse)
    {
        val displayList = ArrayList<MovieDetails>()
        for(b in movie.resultsList)
        {
            displayList.add(b)
        }

        //TODO: Is this tag correct?
        main_recycler.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false )
        //TODO: WHAT TO PASS! :(
        main_recycler.adapter = MovieAdapter(displayList)
    }





    //MovieCallBack members.
    override fun onMovieReady(movie: MovieResponse) {
        //calling data binding function.
        bindMoviesDataWithAdapter(movie)
    }

    override fun onMovieError(errorMsg: String) {
        //TODO: check which activity ???
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }
}