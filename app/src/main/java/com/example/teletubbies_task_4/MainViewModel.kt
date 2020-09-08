package com.example.teletubbies_task_4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), MovieRepository.MovieCallBack {

    private val _movieLiveData: MutableLiveData<MovieResponse>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<MovieResponse>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: MovieResponse

    private var currentMovieName = "Title"

    fun loadMovieData(movieName: String = "") {

        if (movieName == currentMovieName && this::movieData.isInitialized) {
            _movieLiveData.value = movieData
            return
        }

        if (movieName.isNotEmpty())
            currentMovieName = movieName

        MovieRepository.requestMovieData(currentMovieName, this)
    }

    override fun onMovieReady(movie: MovieResponse) {
        movieData = movie
        _movieLiveData.value = movieData
    }

    override fun onMovieError(errorMsg: String) {
        _onError.value = errorMsg
    }
}