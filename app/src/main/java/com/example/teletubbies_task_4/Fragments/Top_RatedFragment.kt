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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        /*Calling the function doTheJob in the onViewCreated because i need it to be called
        again every time the fragment activity pauses and resumes */
        doTheJob()


    }
    //This functions creates and setups our recyclerView.
    private fun setupRecycler(movie: List<MovieRated>) {
        //setting up the linear layout.
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //linking the layout to the recycler.
        main_recycler_2.layoutManager = linearLayoutManager
        //setting up the adapter.
        RvAdapter = MovieRatedAdapter(movie)
        //Linking the adapter with the recycler.
        main_recycler_2.adapter = RvAdapter
    }

    //New one instead of the interface.
    private fun handleMovieError(errorMsg: String) {

        Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
    }
    //This function is all the Magic!
    private fun doTheJob(){
        //Pagination variable set by false, since we didn't load any data.
        var isPaginated = false
        //linear layout variable in order to access .stackFromEnd function.
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //Links MVVM to this activity and gives us the ability to access through the fragment.
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        /*When switching between fragments we saved a bug when switching back where the application
        in order to solve this problem we needed the application to resetup the recyclerview each
        time the user switches back to the fragment, this is done using the variable below.*/
        var initialLoad = true
        //calling live data observing function!
        mainViewModel.movieRatedLiveData.observe(viewLifecycleOwner, {
            //checks if this is first load or a new page.
            if (isPaginated&&!initialLoad) {
                /*if the data has been passed through the initial loading/setup and the user
                tries to load the next page data do the following*/
                //Stack new movies from the end.
                linearLayoutManager.stackFromEnd
                //Update adapter data instead of recreating it! (to avoid starting at top)
                RvAdapter.updateData(it)

            } else {
                //if this is first load/resumed it will set up the recycler.
                setupRecycler(it)
                isPaginated = false
            }
        })
        mainViewModel.onError.observe(viewLifecycleOwner, {
            handleMovieError(it)
        })

        mainViewModel.loadTopRatedMovieData(myPage = page)
        initialLoad = false
        //MVVM PART ENDS HERE

        //Recycler scroll listener for pagination.
        main_recycler_2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    //this means we reached the scroll limit, therefore we increment the page,
                    //in order to load the next page of the code.
                    page++
                    //setting pagination to true so that movieLiveData updates instead of recreating.
                    isPaginated = true
                    //calling load function to load the data of the next page.
                    mainViewModel.loadTopRatedMovieData(myPage = page)

                }
            }
        })
    }


}

