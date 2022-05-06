package com.example.capstone.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.Entities.Notes
import com.example.capstone.R
import kotlinx.android.synthetic.main.item_recyclerview_notes.view.*

class NotesAdapter(val arrList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_notes, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.textViewTitle.text = arrList[position].title
        holder.itemView.textViewDescription.text = arrList[position].noteText
        holder.itemView.textViewDateTime.text = arrList[position].dateTime

        if(arrList[position].color != null){
            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(arrList[position].color))

        } else{
            var stringBuilder = "#"
            stringBuilder += R.color.purple_200.toString()
            holder.itemView.cardView.setCardBackgroundColor(R.color.ColorDefaultNote)
        }
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
}