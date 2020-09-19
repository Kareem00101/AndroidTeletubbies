package com.example.teletubbies_task_4.data.Repository
import android.content.Context
import com.example.teletubbies_task_4.data.MovieMapper
import com.example.teletubbies_task_4.data.Network.ApiInterface
import com.example.teletubbies_task_4.data.Network.ApiClient
import com.example.teletubbies_task_4.data.database.AppDatabase
import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MovieRepository {

    //for calling the API.
    private val apiServices: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }
    //variables we need to call the get from the APIinterface.
    private const val apiKey = "4b7ad36f69f80aa34703d042a53836e4"
    //mapper for linking the data.
    private val mapper by lazy { MovieMapper() }

    private lateinit var appDatabase: AppDatabase

    var pg :Int = 1;

    //This method is to be called in the MVVM.
    fun requestMovieData(lang: String, callback: MovieCallBack)
    {
        while(pg <= 10){
        //calling the interface get method and passing it the needed info.
        apiServices.getMovie(apiKey = apiKey, language = lang,pg)
            .enqueue(object: Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) { //start of onResponse
                    println("Calling onResponse") // a check

                    if(response.isSuccessful)
                    {
                        println("Response Successful")
                        //passing the response data to the var
                        val moviesData = mapper.mapToMovieUi(response.body()!!)

                        appDatabase.getMovieDao().addMovies(moviesData)

                        //passing the data to the implementer of the interface (MVVM).
                        callback.onMovieReady(moviesData)
                    } else if(response.code() in 400..404) {
                        //in case of an error, helps identifying the error.
                        val msg = "an error has occurred"
                        callback.onMovieError(errorMsg = msg)
                    }

                } //end of onResponse

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movies data"
                    //passing the data to the implementer of the interface (MVVM).
                    callback.onMovieError(errorMsg = msg)

                    callback.onMovieReady(appDatabase.getMovieDao().getAllMovies())
                } // end of on failure

            })
            pg++
        }
    }

    fun createDatabase(context: Context) {
        appDatabase = AppDatabase.getDatabase(context)
    }

    //interface class, necessary in order to create an object from the main activity.
    //Main activity must implement the interface.
    interface MovieCallBack
    {
        fun onMovieReady(movie: List<Movie>)
        fun onMovieError(errorMsg: String)
    }

}//end of movieRepository