package com.example.teletubbies_task_4.data.Repository
import android.content.Context
import com.example.teletubbies_task_4.data.MovieMapper
import com.example.teletubbies_task_4.data.MovieRatedMapper
import com.example.teletubbies_task_4.data.Network.ApiInterface
import com.example.teletubbies_task_4.data.Network.ApiClient
import com.example.teletubbies_task_4.data.database.AppDatabase
import com.example.teletubbies_task_4.data.models.remote.MovieRatedResponse
import com.example.teletubbies_task_4.data.models.remote.MovieResponse
import com.example.teletubbies_task_4.data.ui.Movie
import com.example.teletubbies_task_4.data.ui.MovieRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


object MovieRepository {
    //for calling the API.
    private val apiServices: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }
    //variables we need to call the get from the APIinterface.
    private const val apiKey = "4b7ad36f69f80aa34703d042a53836e4"
    //mapper for linking the data.
    private val mapper by lazy { MovieMapper() }
    private val mapperRated: MovieRatedMapper by lazy { MovieRatedMapper() }
    var x = LinkedList<Movie>()
    var y = LinkedList<MovieRated>()
    private lateinit var appDatabase: AppDatabase

    //This method is to be called in the MVVM.
    //Popular API

    fun requestMovieData(lang: String, callback: MovieCallBack, myPage:Int)
    {

        //calling the interface get method and passing it the needed info.
        apiServices.getMovie(apiKey = apiKey, language = lang, page = myPage)
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
                    val msg = "Error while getting popularMovies data"
                    //passing the data to the implementer of the interface (MVVM).
                    callback.onMovieError(errorMsg = msg)

                    callback.onMovieReady(appDatabase.getMovieDao().getAllMovies())
                } // end of on failure

            })
    }

 //Top Rated API
    fun requestTopRatedMovieData (lang: String, ratedcallback: MovieRatedCallBack, myPage:Int) {
     apiServices.getTopRated(apiKey = apiKey, language = lang, page = myPage).enqueue(object: Callback<MovieRatedResponse>
     {
         override fun onResponse(
             call: Call<MovieRatedResponse>,
             response: Response<MovieRatedResponse>
         ) {
             if(response.isSuccessful)
             {
                 println("Response Successful")
                 //passing the response data to the var
                 val moviesRatedData = mapperRated.mapToMovieRatedUi(response.body()!!)

                 appDatabase.getMovieRatedDao().addMovies(moviesRatedData)

                 //passing the data to the implementer of the interface (MVVM).
                 ratedcallback.onMovieRatedReady(moviesRatedData)
             } else if(response.code() in 400..404) {
                 //in case of an error, helps identifying the error.
                 val msg = "an error has occurred"
                 ratedcallback.onMovieRatedError(errorMsg = msg)
             }
         }

         override fun onFailure(call: Call<MovieRatedResponse>, t: Throwable) {
             t.printStackTrace()
             val msg = "Error while getting topRatedMovies data"
             //passing the data to the implementer of the interface (MVVM).
             ratedcallback.onMovieRatedError(errorMsg = msg)

             ratedcallback.onMovieRatedReady(MovieRepository.appDatabase.getMovieRatedDao().getAllMovies())
            }

        })
     }
    //This function is used for the favoriteList implementation.
    //This function is called in the MovieDetails Activity inside the btn click listener.
    //The ID is used to determine the item i want to add or remove.
    //the ADD boolean variable depends on the user desire to add or remove from the favoriteList.
    fun requestForFavorite(myMovieID:Long, Add: Boolean){
        apiServices.getMovie(apiKey = apiKey, page = 1).enqueue(object: Callback<MovieResponse>
        {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                println("Response Successful")
                //passing the response data to the var
                val moviesData = mapper.mapToMovieUi(response.body()!!)
                //Here comes the magic!
                //if the ID is equal and the user wants to add then add.
                //if the ID is not equal and the user wants to remove then remove.
                //The isFavorite boolean variable is used in the local class and the database.
                moviesData.forEach { it ->
                    if(myMovieID == it.id && Add)
                    {
                        println("Added to PopularFavoriteLinkedList")
                        it.isFavorite = true
                        x.add(it)
                    }else if(myMovieID == it.id && !Add)
                    {
                        println("Removed from PopularFavoriteLinkedList")
                        it.isFavorite = false
                        x.remove(it)
                    }
                }
                //adding the modified list to the database.
                appDatabase.getMovieDao().addMovies(x)
            }
            //in case of failure.
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                val msg = "Error while getting PopularFavoriteList data"
            }
        })
        apiServices.getTopRated(apiKey = apiKey, page=1).enqueue(object: Callback<MovieRatedResponse>
        {
            override fun onResponse(
                call: Call<MovieRatedResponse>,
                response: Response<MovieRatedResponse>
            ) {
                val moviesData: List<MovieRated> = mapperRated.mapToMovieRatedUi(response.body()!!)
                //Here comes the magic!
                //if the ID is equal and the user wants to add then add.
                //if the ID is not equal and the user wants to remove then remove.
                //The isFavorite boolean variable is used in the local class and the database.
                moviesData.forEach{
                    if(myMovieID == it.id && Add)
                    {
                        println("Added to TopRatedFavoriteLinkedList")
                        it.isFavorite = true
                        y.add(it)

                    }else if(myMovieID == it.id && !Add)
                    {
                        println("Removed to TopRatedFavoriteLinkedList")
                        it.isFavorite = false
                        y.remove(it)
                    }
                }
                //Adding the new list to the database.
                appDatabase.getMovieRatedDao().addMovies(y)
            }
            //in case of failure.
            override fun onFailure(call: Call<MovieRatedResponse>, t: Throwable) {
                t.printStackTrace()
                val msg = "Error while getting topRatedFavoriteList data"
            }
        })
    }


    //creating the data base.
    fun createDatabase(context: Context) {
        appDatabase = AppDatabase.getDatabase(context)
    }
    //The functions below are for the FavoriteList implementation.
    //returns to me the popularFavoriteList to be used in the FavoritePopularFragment for the adapter
    fun getFavorite() : List<Movie>
    {
        return appDatabase.getMovieDao().getFavoriteMovies()
    }
    //returns to me the TopRatedFavoriteList to be used in the TopRatedPopularFragment for the adapter
    fun getTopRatedFavorite() : List<MovieRated>
    {
        return appDatabase.getMovieRatedDao().getFavoriteTopRatedMovies()
    }
    //End of functions needed for the favoriteList Implementation.

    //interface class, necessary in order to create an object from the main activity.
    //Main activity must implement the interface.
    interface MovieCallBack
    {
        fun onMovieReady(movie: List<Movie>)
        fun onMovieError(errorMsg: String)

    }
    interface MovieRatedCallBack
    {
        fun onMovieRatedReady (movieRated : List<MovieRated>)
        fun onMovieRatedError(errorMsg: String)
    }

}//end of movieRepository