package com.example.takenotes.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.takenotes.DButils.NotesData
import com.example.takenotes.R
import com.example.takenotes.ViewModels.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddOrEditActivity : AppCompatActivity() {
    lateinit var edtnoteTitle:EditText
    lateinit var noteDesc:EditText
    lateinit var saveBtn:Button
    lateinit var notesViewModel: NotesViewModel
    var notesId=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit)
        edtnoteTitle=findViewById(R.id.idEdtNoteId)
        noteDesc=findViewById(R.id.idEdtNoteDesc)
        saveBtn=findViewById(R.id.idBtn)
        notesViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(NotesViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            notesId = intent.getIntExtra("noteId", -1)
            saveBtn.setText("Update Note")
            edtnoteTitle.setText(noteTitle)
            noteDesc.setText(noteDescription)
        } else {
            saveBtn.setText("Save Note")
        }

        saveBtn.setOnClickListener {
            val noteTitle = edtnoteTitle.text.toString()
            val noteDescription = noteDesc.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = NotesData(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = notesId
                   notesViewModel.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    notesViewModel.insertNote(NotesData(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

}