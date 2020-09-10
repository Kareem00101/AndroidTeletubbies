package com.example.teletubbies_task_4.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teletubbies_task_4.data.Repository.MovieRepository
import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie

class MainViewModel : ViewModel(), MovieRepository.MovieCallBack {

    //Notes: A MutableLiveData can be set..
    //Notes: While LiveData cannot be set.
    //Always use LiveData outside this class for security reasons.
    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData

    //Error Data.
    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<Movie>
    private var currentMovieLang = "English"

    //The function below is to be used inside the activity.
    fun loadMovieData(movieLang: String = "") {
        //The Logic below is for future purposes if we add a language choosing functionality.
        if (movieLang == currentMovieLang && this::movieData.isInitialized) {
            _movieLiveData.value = movieData
            return
        }

        if (movieLang.isNotEmpty())
            currentMovieLang = movieLang

        //Calling requestMovieData function inside the repository file.
        MovieRepository.requestMovieData(currentMovieLang, this)
    }

    //The implementation of the interface class inside the repository file.
    //Linking the response data to our live data.
    override fun onMovieReady(movie: List<Movie>) {
        movieData = movie
        _movieLiveData.value = movieData
    }

    override fun onMovieError(errorMsg: String) {
        _onError.value = errorMsg
    }
}