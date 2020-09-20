package com.example.teletubbies_task_4.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teletubbies_task_4.Fragments.*
import com.example.teletubbies_task_4.R
import com.example.teletubbies_task_4.data.ui.Movie
import kotlinx.android.synthetic.main.activity_recyclerview_movies.*

//This is the activity that contains the recycler view.
class RecyclerViewMovies : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()


// fragment part for the menu bar
    private val settingsFragment = SettingsFragment()
    private val favouriteFragment= FavouriteFragment()
    private val topRatedfragment= Top_RatedFragment()
    private val popularFragment= popularrFragment()
    private val homeFragment= HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)
        //MVVM PART START HERE
        mainViewModel.movieLiveData
            .observe(this, {
                //button_Preview.isEnabled = true
                bindMoviesDataWithAdapter(it) })

        mainViewModel.onError.observe(this, {
            handleMovieError(it)
        })

        mainViewModel.loadMovieData()
        //MVVM PART ENDS HERE

//fragments
//        replaceFragment(settingsFragment)
        makeCurrentFragment(homeFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Settings -> makeCurrentFragment(settingsFragment)
                R.id.favourite -> makeCurrentFragment(favouriteFragment)
                R.id.TopRated -> makeCurrentFragment(topRatedfragment)
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.popular -> makeCurrentFragment(popularFragment)
            }
            true
        }

    }

    //This functions links data source with the adapter.
    private fun bindMoviesDataWithAdapter(movie: List<Movie>)
    {
        //Designing recycler view and linking it to the adapter.
        main_recycler.layoutManager = LinearLayoutManager(this@RecyclerViewMovies,
            LinearLayoutManager.VERTICAL, false )
        main_recycler.adapter = MovieAdapter(movie)
    }

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(this@RecyclerViewMovies, errorMsg, Toast.LENGTH_LONG).show()
    }

    //FRAGMENT
    private fun makeCurrentFragment(fragment: Fragment){
//        if (fragment !=null) {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container,fragment)
//            transaction.commit()
//        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}