package com.example.takenotes.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.takenotes.Adapter.NotesAdapter
import com.example.takenotes.Adapter.NotesClickInterface
import com.example.takenotes.Adapter.NotesDeleteInterface
import com.example.takenotes.DButils.NotesData
import com.example.takenotes.R
import com.example.takenotes.ViewModels.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() ,NotesDeleteInterface,NotesClickInterface{
    lateinit var viewModal: NotesViewModel
   lateinit var  notesRv:RecyclerView
    lateinit var floatingBtn:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRv=findViewById(R.id.notesRV)
        floatingBtn=findViewById(R.id.floatingActionBtn)
        notesRv.layoutManager=LinearLayoutManager(this)
        val noteRvAdapter=NotesAdapter(this,this,this)
        notesRv.adapter=noteRvAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)

        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRvAdapter.updateList(it)
            }
        })
        floatingBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddOrEditActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


    override fun onNotesClick(notesData: NotesData) {

        val intent = Intent(this@MainActivity, AddOrEditActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", notesData.NoteTitle)
        intent.putExtra("noteDescription", notesData.NoteDec)
        intent.putExtra("noteId", notesData.id)
        startActivity(intent)
        this.finish()
    }



    override fun onNotesDelete(notesData: NotesData) {
        viewModal.deleteNote(notesData)
        Toast.makeText(this, "${notesData.NoteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
    }