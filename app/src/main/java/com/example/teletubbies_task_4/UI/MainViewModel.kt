package com.example.teletubbies_task_4.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teletubbies_task_4.data.Repository.MovieRepository
import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie

class MainViewModel : ViewModel(), MovieRepository.MovieCallBack {

    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData

    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<Movie>

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
        movieData = movie
        _movieLiveData.value = movieData
    }

    override fun onMovieError(errorMsg: String) {
        _onError.value = errorMsg
    }
}