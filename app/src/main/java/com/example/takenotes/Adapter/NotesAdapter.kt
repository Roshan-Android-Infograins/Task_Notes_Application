package com.example.takenotes.Adapter

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import com.example.takenotes.DButils.NotesData
import com.example.takenotes.R

class NotesAdapter(
    val context: Context,
    val notesClickInterface: NotesClickInterface,
    val notesDeleteInterface: NotesDeleteInterface
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
  private  val allNotes = ArrayList<NotesData>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notesTitle = itemView.findViewById<TextView>(R.id.Notetitle)
        val notesTime = itemView.findViewById<TextView>(R.id.Notestime)
        val Deletenote = itemView.findViewById<ImageView>(R.id.deleteNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.notes_rv_layout,
            parent, false
        )
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }


    fun updateList(newList: List<NotesData>) {

        newList.forEach {
            Log.e("CheckMyData",it.NoteTitle+"\t"+it.NoteDec)
        }

        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notesTitle.setText(allNotes.get(position).NoteTitle)
        holder.notesTime.setText("Last updated"+allNotes.get(position).NoteTime)
        holder.Deletenote.setOnClickListener { notesDeleteInterface.onNotesDelete(allNotes.get(position)) }
        holder.itemView.setOnClickListener { notesClickInterface.onNotesClick(allNotes.get(position)) }
    }
}

interface NotesDeleteInterface {
    fun onNotesDelete(notesData: NotesData)
}

interface NotesClickInterface {
    fun onNotesClick(notesData: NotesData)
}
