package com.example.teletubbies_task_4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teletubbies_task_4.R
import com.example.teletubbies_task_4.UI.MovieAdapter
import com.example.teletubbies_task_4.UI.MovieRatedAdapter
import com.example.teletubbies_task_4.data.Repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_favourite.main_recycler_3
import kotlinx.android.synthetic.main.fragment_favourite_top_rated.*

class FavouriteTopRatedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_top_rated, container, false)
    }
    private lateinit var RvAdapter: MovieRatedAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setting up the recycler with the favorite list.
        //if(!MovieRepository.y.isNullOrEmpty()) {
            setupRecycler()
        //}

    }
    private fun setupRecycler() {
        //setting up the linear layout.
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //linking the layout to the recycler.
        main_recycler_4.layoutManager = linearLayoutManager
        //setting up the adapter.
        RvAdapter = MovieRatedAdapter(MovieRepository.getTopRatedFavorite())
        //linking the adapter to the recycler.
        main_recycler_4.adapter = RvAdapter
    }


}