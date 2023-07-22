package com.example.takenotes.DButils

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note:NotesData)

    @Update
    suspend fun updateNote(note: NotesData)

    @Delete
    suspend fun delete(note: NotesData)

    @Query("Select * from NoteData order by id ASC")
    fun getCompleteData():LiveData<List<NotesData>>
}