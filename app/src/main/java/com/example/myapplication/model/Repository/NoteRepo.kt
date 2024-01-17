package com.example.myapplication.model.Repository

import androidx.lifecycle.LiveData
import com.example.myapplication.model.DB.NoteDAO
import com.example.myapplication.model.DB.NoteEntity
import javax.inject.Inject


class NoteRepo @Inject constructor(private val noteDAO: NoteDAO) {
    suspend fun saveNote(note: NoteEntity) {
        noteDAO.saveNote(note)
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return noteDAO.getAllNotes()
    }
}