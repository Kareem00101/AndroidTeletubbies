package com.example.teletubbies_task_4.UI

//import com.example.teletubbies_task_4.data.Repository.MovieRepository.pg
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.teletubbies_task_4.R
import com.example.teletubbies_task_4.data.ui.Movie
import com.squareup.picasso.Picasso


//An adapter class is very necessary for the functioning of the recycler view, as it links the data
//from the data source to the item view holder holder.
class MovieAdapter(private var movieList: List<Movie>) : RecyclerView.Adapter<NoteViewHolder>()
{
    //A variable for pagination bug trial.
    //private val items: ArrayList<Movie>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieView = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val movieLinker = movieList.get(position)

        //Binding movieDetails with the item_note through the adapter.
        holder.movieLanguage.text = movieLinker.language
        holder.movieRating.text   = movieLinker.rating.toString()
        holder.movieRelease.text  = movieLinker.release
        holder.movieTitle.text    = movieLinker.title

        //Binding images using Picasso.
        //In order to generate a fully working image URL, you'll need 3 pieces of data.
        //Those pieces are a base_url, a file_size and a file_path.
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movieLinker.posterPortrait}").into(
            holder.moviePoster

        )


        //For second screen ( movie description screen )
        val bundle: Bundle = Bundle()
        bundle.putString("movie_description", movieLinker.description)
        bundle.putString("title", movieLinker.title)
        //Setting the click listener on the whole item, since we got no buttons.
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)

        }
    }
    //this functions returns the list size.
    override fun getItemCount(): Int {
       return movieList.size
    }
    //A trial for solving the bug
    /*//For pagination
    public interface updateRecyclerDataCaller{
        fun updateData(movieList: List<Movie>)
    }*/
    //Another trial for solving Pagination Bug.
    //
    open fun updateData(movieListNext: List<Movie>) {
        //items!!.clear()
        //items!!.addAll(movieList)
        movieList = movieListNext
        notifyDataSetChanged()
    }



}
