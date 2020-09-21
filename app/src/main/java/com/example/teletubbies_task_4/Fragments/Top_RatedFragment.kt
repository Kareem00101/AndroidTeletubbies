package com.example.teletubbies_task_4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.teletubbies_task_4.R
import com.example.teletubbies_task_4.UI.MainViewModel
import com.example.teletubbies_task_4.UI.MovieAdapter
import com.example.teletubbies_task_4.UI.MovieRatedAdapter
import com.example.teletubbies_task_4.data.ui.Movie
import com.example.teletubbies_task_4.data.ui.MovieRated
import kotlinx.android.synthetic.main.fragment_popularr.*
import kotlinx.android.synthetic.main.fragment_top__rated.*

class Top_RatedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_top__rated, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        //MVVM PART START HERE
        mainViewModel.movieRatedLiveData
            .observe(viewLifecycleOwner, {
                //button_Preview.isEnabled = true
                bindMoviesDataWithAdapter(it)
            })

        mainViewModel.onError.observe(viewLifecycleOwner, {
            handleMovieError(it)
        })

        mainViewModel.loadTopRatedMovieData()
        //MVVM PART ENDS HERE
    }
    //This functions links data source with the adapter.
    private fun bindMoviesDataWithAdapter(movie: List<MovieRated>)
    {
        //Designing recycler view and linking it to the adapter.
        main_recycler_2.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL, false )
        main_recycler_2.adapter = MovieRatedAdapter(movie)
    }

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
    }



}

