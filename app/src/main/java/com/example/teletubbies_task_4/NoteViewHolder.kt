package com.example.teletubbies_task_4

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    val note_posterPortrait :ImageView = itemView.posterPortrait
    val note_title : TextView = itemView.title
    val note_rating : TextView = itemView.rating
    val note_language : TextView = itemView.language
    val note_release : TextView = itemView.release
}