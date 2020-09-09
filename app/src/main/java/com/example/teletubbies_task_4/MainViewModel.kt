package com.example.teletubbies_task_4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), MovieRepository.MovieCallBack {

    private val _movieLiveData: MutableLiveData<List<Movie>>
    //private val _movieLiveData: MutableLiveData<MovieResponse>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
    //val movieLiveData: LiveData<MovieResponse>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<Movie>
    //private lateinit var movieData: MovieResponse

    private var currentMovieLang = "English"

    fun loadMovieData(movieLang: String = "") {

        if (movieLang == currentMovieLang && this::movieData.isInitialized) {
            _movieLiveData.value = movieData
            return
        }

        if (movieLang.isNotEmpty())
            currentMovieLang = movieLang

        MovieRepository.requestMovieData(currentMovieLang, this)
    }


    override fun onMovieReady(movie: List<Movie>) {
    //override fun onMovieReady(movie: MovieResponse) {
      // movieData = movie
      // _movieLiveData.value = movieData
        _movieLiveData.value = movie

    }

    override fun onMovieError(errorMsg: String) {
        _onError.value = errorMsg
    }
}