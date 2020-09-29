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
import kotlinx.android.synthetic.main.fragment_popularr.*

//This is the activity that contains the recycler view.
class RecyclerViewMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_movies)

        val settingsFragment = SettingsFragment()
        val favouriteFragment= FavouriteFragment()
        val topRatedFragment= Top_RatedFragment()
        val popularFragment= popularrFragment()
        val homeFragment= HomeFragment()
val favouriteTopRatedFragment = FavouriteTopRatedFragment ()



//fragments
//        replaceFragment(settingsFragment)
        makeCurrentFragment(homeFragment)
        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.Settings -> makeCurrentFragment(settingsFragment)
                R.id.favourite -> makeCurrentFragment(favouriteFragment)
                R.id.TopRated -> makeCurrentFragment(topRatedFragment)
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.popular -> makeCurrentFragment(popularFragment)
                R.id.favouriteTopRated -> makeCurrentFragment(favouriteFragment)

            }
            true
        }

    }
    //FRAGMENT
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment).commit()
        }
    }
}