package com.example.takenotes.Reposetry

import androidx.lifecycle.LiveData
import com.example.takenotes.DButils.NoteDao
import com.example.takenotes.DButils.NotesData

class NotesReposetry(private val notesDao :NoteDao) {

    val allNotesData:LiveData<List<NotesData>> = notesDao.getCompleteData()

    suspend fun insert(note:NotesData){
        notesDao.insertNote(note)
    }
    suspend fun delete(note:NotesData){
        notesDao.delete(note)
    }
    suspend fun update(note:NotesData){
        notesDao.updateNote(note)
    }

}