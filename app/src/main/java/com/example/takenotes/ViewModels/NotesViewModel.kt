package com.example.takenotes.ViewModels

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.example.takenotes.DButils.NoteDataBase
import com.example.takenotes.DButils.NotesData
import com.example.takenotes.Reposetry.NotesReposetry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val allNotes : LiveData<List<NotesData>>
    val reposetry:NotesReposetry

    init {
        val dao=NoteDataBase.getNotesDataBase(application).getNoteDao()
        reposetry= NotesReposetry(dao)
        allNotes=reposetry.allNotesData
    }

    fun deleteNote(note:NotesData)=viewModelScope.launch (Dispatchers.IO){
        reposetry.delete(note)
    }


    fun updateNote(note:NotesData)=viewModelScope.launch (Dispatchers.IO){
        reposetry.update(note)
    }
    fun insertNote(note:NotesData)=viewModelScope.launch (Dispatchers.IO){
        reposetry.insert(note)
    }

}