package com.example.teletubbies_task_4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



object MovieRepository {

    private val apiServices: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }
    //variables we need to call the get from the APIinterface.
    private const val apiKey = "4b7ad36f69f80aa34703d042a53836e4"
    private var movieLang = "English"
    private val mapper by lazy { MovieMapper() }

    //In case of a successful response this variable should carry the response.body.
    //private lateinit var moviesData: MovieResponse

    fun requestMovieData(lang: String = movieLang,callback: MovieCallBack )
    {
        /*if(this::moviesData.isInitialized && lang == movieLang)
        {
            //checking if the passed data is suitable for calling.
            callback.onMovieReady(moviesData)
            return
        }*/
        //in order to keep the language as it is, in case of rotating.
        //the whole language code might be unnecessary but we used it for testing purposes.
        movieLang = lang

        //calling the interface get method and passing it the needed info.
        apiServices.getMovie(apiKey = apiKey, language = lang)
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
                        val moviedata = mapper.mapToMovieUi(response.body()!!)
                        callback.onMovieReady(moviedata)
                    } else if(response.code() in 400..404) {
                        //in case of an error, helps identifying the error.
                        val msg = "an error has occurred"
                        callback.onMovieError(errorMsg = msg)
                    }

                } //end of onResponse

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    val msg = "Error while getting movies data"
                    callback.onMovieError(errorMsg = msg)
                } // end of on failure

            })
    }



    //interface class, necessary in order to create an object from the main activity.
    //Main activity must implement the interface.
    interface MovieCallBack
    {
        fun onMovieReady(movie: List<Movie>)
        fun onMovieError(errorMsg: String)
    }
}//end of movieRepository