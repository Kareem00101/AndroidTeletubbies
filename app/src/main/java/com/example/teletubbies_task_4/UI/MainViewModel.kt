package com.example.teletubbies_task_4.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teletubbies_task_4.data.Repository.MovieRepository
import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie
import com.example.teletubbies_task_4.data.ui.MovieRated

class MainViewModel(application: Application)  : AndroidViewModel(application),
    MovieRepository.MovieCallBack,MovieRepository.MovieRatedCallBack {

    //Notes: A MutableLiveData can be set..
    //Notes: While LiveData cannot be set.
    //Always use LiveData outside this class for security reasons.
    private val _movieLiveData: MutableLiveData<List<Movie>>
            by lazy { MutableLiveData() }
    val movieLiveData: LiveData<List<Movie>>
        get() = _movieLiveData
    private val _movieRatedLiveData: MutableLiveData<List<MovieRated>>
            by lazy { MutableLiveData() }
    val movieRatedLiveData: LiveData<List<MovieRated>>
        get() = _movieRatedLiveData

    //Error Data.
    private val _onError: MutableLiveData<String>
            by lazy { MutableLiveData() }
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<Movie>
    private var currentMovieLang = "English"
    private lateinit var movieRatedData : List<MovieRated>
    private var currentMovieRatedLang = "English"

    init {
        MovieRepository.createDatabase(application)
    }


    //The function below is to be used inside the activity.(PopularMovies)
    fun loadMovieData(movieLang: String = "", myPage:Int) {
        //The Logic below is for future purposes if we add a language choosing functionality.
        if (movieLang == currentMovieLang && this::movieData.isInitialized) {
            _movieLiveData.value = movieData
            return
        }

        if (movieLang.isNotEmpty())
            currentMovieLang = movieLang

        //Calling requestMovieData function inside the repository file.
        MovieRepository.requestMovieData(currentMovieLang, this, myPage)
    }
    //The function below is to be used inside the activity.(Top Rated Movies)
    fun loadTopRatedMovieData(movieRatedLang: String = "", myPage: Int) {
        //The Logic below is for future purposes if we add a language choosing functionality.
        if (movieRatedLang == currentMovieRatedLang  && this::movieRatedData.isInitialized) {
            _movieRatedLiveData.value = movieRatedData
            return
        }

        if (movieRatedLang.isNotEmpty())
            currentMovieRatedLang  = movieRatedLang

        //Calling requestMovieData function inside the repository file.
        MovieRepository.requestTopRatedMovieData(currentMovieRatedLang , this, myPage)
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

    override fun onMovieRatedReady(movieRated: List<MovieRated>) {
        movieRatedData = movieRated
        _movieRatedLiveData.value = movieRatedData
    }

    override fun onMovieRatedError(errorMsg: String) {
        _onError.value = errorMsg
    }

    //for favoriteList
    fun createMe(){println("main view model created")}
}