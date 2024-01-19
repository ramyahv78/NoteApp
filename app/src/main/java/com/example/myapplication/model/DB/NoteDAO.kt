package com.example.myapplication.model.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Insert
    suspend fun saveNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteAll(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table")
    suspend fun getAllNotes(): List<NoteEntity>
}