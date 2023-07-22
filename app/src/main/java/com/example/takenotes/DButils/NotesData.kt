package com.example.takenotes.DButils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteData")
class NotesData(
    @ColumnInfo(name = "NoteTitle")val NoteTitle:String,
    @ColumnInfo(name = "NoteDec")val NoteDec:String,
    @ColumnInfo(name = "NoteTime")val NoteTime:String) {
    @PrimaryKey(autoGenerate = true)var id = 0
}