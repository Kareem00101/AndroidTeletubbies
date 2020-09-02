package com.example.teletubbies_task_4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//An adapter class is very necessary for the functioning of the recycler view, as it links the data
//from the data source to the item view holder holder.
class MovieAdapter (private val movieList: List<MovieDetails>) : RecyclerView.Adapter<NoteViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieView = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val iDunKnow = movieList.get(position)

        //The code below was for testing purposes.
        /*holder.movieLanguage.text = iDunKnow.resultsList[position].language
        holder.movieRating.text   = iDunKnow.resultsList[position].rating.toString()
        holder.movieRelease.text  = iDunKnow.resultsList[position].release
        holder.movieTitle.text    = iDunKnow.resultsList[position].title*/

        //Binding movieDetails with the item_note through the adapter.
        holder.movieLanguage.text = iDunKnow.language
        holder.movieRating.text   = iDunKnow.rating.toString()
        holder.movieRelease.text  = iDunKnow.release
        holder.movieTitle.text    = iDunKnow.title
    }

    //this functions returns the list size.
    override fun getItemCount(): Int {
       return movieList.size
    }
}