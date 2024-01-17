package com.example.myapplication.model.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "dates") val date: String,
    @ColumnInfo(name = "notes") val note: String
) {

}