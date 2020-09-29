package com.example.teletubbies_task_4.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teletubbies_task_4.R
import com.example.teletubbies_task_4.UI.MainViewModel
import com.example.teletubbies_task_4.UI.MovieAdapter
import com.example.teletubbies_task_4.data.Repository.MovieRepository
import com.example.teletubbies_task_4.data.database.AppDatabase
import com.example.teletubbies_task_4.data.ui.Movie
import kotlinx.android.synthetic.main.fragment_popularr.*


class FavouriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }
    private lateinit var RvAdapter: MovieAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        /*val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.movieLiveData.observe(viewLifecycleOwner,{
            setupRecycler()
        })*/

    }


    private fun setupRecycler() {
        //setting up the linear layout.
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //linking the layout to the recycler.
        main_recycler_3.layoutManager = linearLayoutManager
        //setting up the adapter.
        RvAdapter = MovieAdapter(MovieRepository.getFavorite())
        //linking the adapter to the recycler.
        main_recycler_3.adapter = RvAdapter
    }

}