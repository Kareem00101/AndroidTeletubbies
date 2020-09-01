package com.example.teletubbies_task_4

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    val moviePoster :ImageView = itemView.posterPortrait
    val movieTitle : TextView = itemView.title
    val movieRating : TextView = itemView.rating
    val movieLanguage : TextView = itemView.language
    val movieRelease : TextView = itemView.release

}