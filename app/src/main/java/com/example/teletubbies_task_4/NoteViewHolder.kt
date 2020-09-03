package com.example.teletubbies_task_4

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    //variables referencing to the item_note layout views.
    val moviePoster :ImageView = itemView.posterPortrait
    val movieTitle : TextView = itemView.titleOfMovie
    val movieRating : TextView = itemView.ratingOfMovie
    val movieLanguage : TextView = itemView.languageOfMovie
    val movieRelease : TextView = itemView.releaseDate


}
/*init {
    itemView.setOnClickListener {
        Toast.makeText(
            itemView.context,
            "Hello hope it works.",
            Toast.LENGTH_SHORT
        ).show()
    }
}*/