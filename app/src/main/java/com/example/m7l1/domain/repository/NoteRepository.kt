package com.example.m7l1.domain.repository

import com.example.m7l1.domain.model.Note

interface NoteRepository {

    fun createNote(note: Note)

    fun getAllNotes(): List<Note>

    fun editNote(note: Note)

    fun deleteNote(note: Note)
}