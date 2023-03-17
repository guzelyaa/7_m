package com.example.m7l1.data.repository

import com.example.m7l1.data.base.BaseRepository
import com.example.m7l1.data.local.NoteDao
import com.example.m7l1.data.toNote
import com.example.m7l1.data.totNoteEntity
import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.repository.NoteRepository
import com.example.m7l1.domain.utils.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository,BaseRepository() {


    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.totNoteEntity())
    }


    override fun getAllNotes(): Flow<ResultStatus<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }


    override fun editNote(note: Note) = doRequest {
        noteDao.editNote(note.totNoteEntity())
    }


    override fun deleteNote(note: Note) = doRequest {
        noteDao.deleteNote(note.totNoteEntity())
    }
}