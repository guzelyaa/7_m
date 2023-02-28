package com.example.m7l1.data.local

import androidx.room.*
import com.example.m7l1.data.model.NoteEntity

@Dao
interface NoteDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes():List<NoteEntity>

    @Update
    fun editNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)
}