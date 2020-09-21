package com.example.teletubbies_task_4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
    //A var of adapter for functions accessibility
    private lateinit var RvAdapter: MovieRatedAdapter
    //Page variable for pagination
    var page = 1
    //Pagination bug fix trial
    var isPagination = false
    val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        //MVVM PART START HERE
        mainViewModel.movieRatedLiveData.observe(viewLifecycleOwner, {
            //checks if this is first load or a new page.
            if (isPagination) {
                linearLayoutManager.stackFromEnd
                RvAdapter.updateData(it)
                //if this is first load it will set up the recycler.
            }else {
                setupRecycler(it)
            }
        })

        mainViewModel.onError.observe(viewLifecycleOwner, {
            handleMovieError(it)
        })

        mainViewModel.loadTopRatedMovieData(myPage = page)
        //MVVM PART ENDS HERE
        main_recycler_2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    //this means we reached the scroll limit, therefore we increment the page,
                    //in order to load the next page of the code.
                    page++
                    //setting pagination to true so that movieLiveData updates instead of recreating.
                    isPagination = true
                    //calling load function to load the data of the next page.
                    mainViewModel.loadTopRatedMovieData(myPage = page)

                }
            }
        })
    }
    //This functions links data source with the adapter.
    private fun bindMoviesDataWithAdapter(movie: List<MovieRated>)
    {
        //Designing recycler view and linking it to the adapter.
        main_recycler_2.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL, false )
        main_recycler_2.adapter = MovieRatedAdapter(movie)
    }
    private fun setupRecycler(movie: List<MovieRated>) {

        main_recycler_2.layoutManager = linearLayoutManager
        RvAdapter = MovieRatedAdapter(movie)
        main_recycler_2.adapter = RvAdapter
    }

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
    }



}

