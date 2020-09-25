package com.example.teletubbies_task_4.Fragments

import android.content.Context
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
import com.example.teletubbies_task_4.UI.RecyclerViewMovies
import com.example.teletubbies_task_4.data.ui.Movie
import kotlinx.android.synthetic.main.fragment_popularr.*

class popularrFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_popularr, container, false)
        // Inflate the layout for this fragment
        return view
    }
    //A var of adapter for functions accessibility
    private lateinit var RvAdapter: MovieAdapter
    //Page variable for pagination
    var page = 1
    //Pagination bug fix trial
    var isPagination = false
    //var amIBack: Boolean = false
    //val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doTheJob()
        //doItFast()
       /*val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.movieLiveData.observe(viewLifecycleOwner, {
            setupRecycler(it)
        })
        mainViewModel.onError.observe(viewLifecycleOwner, {
            handleMovieError(it)
        })
        mainViewModel.loadMovieData(myPage = 1)*/





        /*main_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    //this means we reached the scroll limit, therefore we increment the page,
                    //in order to load the next page of the code.
                    page++
                    //setting pagination to true so that movieLiveData updates instead of recreating.
                    isPagination = true
                    //calling load function to load the data of the next page.
                    mainViewModel.loadMovieData(myPage = page)

                }
            }
        })*/
    }
        //This functions links data source with the adapter.
        private fun bindMoviesDataWithAdapter(movie: List<Movie>)
        {
            //Designing recycler view and linking it to the adapter.
            main_recycler.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL, false )
            main_recycler.adapter = MovieAdapter(movie)
        }
        private fun setupRecycler(movie: List<Movie>) {
            val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        main_recycler.layoutManager = linearLayoutManager
        RvAdapter = MovieAdapter(movie)
        main_recycler.adapter = RvAdapter
        }

        //New one instead of the interface.
        private fun handleMovieError(errorMsg: String) {

            Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
        }
    private fun doTheJob(){
        var isPaginated = false
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var firstTime = true
        //var isPagination = false
        //MVVM PART START HERE
        //if(firstTime){doItFast()}
       //else {
            mainViewModel.movieLiveData.observe(viewLifecycleOwner, {
                //checks if this is first load or a new page.
                if (isPaginated&&!firstTime) {
                    linearLayoutManager.stackFromEnd
                    RvAdapter.updateData(it)
                    //if this is first load it will set up the recycler.
                } else {
                    setupRecycler(it)
                    isPaginated = false
                }
            })
            mainViewModel.onError.observe(viewLifecycleOwner, {
                handleMovieError(it)
            })

            mainViewModel.loadMovieData(myPage = page)
            firstTime = false
            //MVVM PART ENDS HERE
            //For pagination
            main_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        //this means we reached the scroll limit, therefore we increment the page,
                        //in order to load the next page of the code.
                        page++
                        //setting pagination to true so that movieLiveData updates instead of recreating.
                        isPaginated = true
                        //calling load function to load the data of the next page.
                        mainViewModel.loadMovieData(myPage = page)

                    }
                }
            })
        //}
    }
    private fun doItFast()
    {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.movieLiveData.observe(viewLifecycleOwner, {
            setupRecycler(it)
        })
        mainViewModel.onError.observe(viewLifecycleOwner, {
            handleMovieError(it)
        })
        mainViewModel.loadMovieData(myPage = page)
    }

    override fun onPause() {
        super.onPause()
        //amIBack = true
    }
    fun bindMovieData( movie: List<Movie>, recyclerView: RecyclerView, isPagination:Boolean)
    {
        val adapter = MovieAdapter(movie)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        if(isPagination)
        {
            adapter.updateData(movie)
            layoutManager.stackFromEnd
            return
        }
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }



    }

