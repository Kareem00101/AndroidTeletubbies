package com.example.teletubbies_task_4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter (private val movieList: List<MovieResponse>) : RecyclerView.Adapter<NoteViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieView = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val iDunKnow = movieList.get(position)
        holder.movieLanguage.text = iDunKnow.resultsList[position].language
        holder.movieRating.text   = iDunKnow.resultsList[position].rating.toString()
        holder.movieRelease.text  = iDunKnow.resultsList[position].release
        holder.movieTitle.text    = iDunKnow.resultsList[position].title
    }

    override fun getItemCount(): Int {
       return movieList.size
    }

}