package com.example.teletubbies_task_4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details.view.*


//An adapter class is very necessary for the functioning of the recycler view, as it links the data
//from the data source to the item view holder holder.
class MovieAdapter(private val movieList: List<MovieDetails>) : RecyclerView.Adapter<NoteViewHolder>()
{
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
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movieLinker.posterPortrait}").into(holder.moviePoster)


        //For second screen
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            //intent.putExtra(movieList)[position]
            holder.itemView.context.startActivity(intent)
            //val holder2: MovieDescriptionHolder
            //holder2.movieOverview.text = iDunKnow.description
            //holder2 = MovieDescriptionHolder()
            //bindSecondScreenData(holder2, iDunKnow)
        }
    }

    //this functions returns the list size.
    override fun getItemCount(): Int {
       return movieList.size
    }
     class MovieDescriptionHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        //variables referencing to the item_note layout views.
        val movieOverview: TextView = itemView.movieOverviewText
    }
    private fun bindSecondScreenData(holder2: MovieDescriptionHolder, x: MovieDetails)
    {
        holder2.movieOverview.text = x.description
    }
}
