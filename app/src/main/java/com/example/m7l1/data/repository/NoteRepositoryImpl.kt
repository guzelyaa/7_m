package com.example.m7l1.data.repository

import com.example.m7l1.data.local.NoteDao
import com.example.m7l1.data.toNote
import com.example.m7l1.data.totNoteEntity
import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun createNote(note: Note) {
        noteDao.createNote(note.totNoteEntity())
    }

    override fun getAllNotes(): List<Note> {
        val data = noteDao.getAllNotes().map { it.toNote() }
        return data
    }

    override fun editNote(note: Note) {
        noteDao.editNote(note.totNoteEntity())
    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNote(note.totNoteEntity())
    }
}