package com.example.m7l1.domain.repository

import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun createNote(note: Note): Flow<ResultStatus<Unit>>

    fun getAllNotes(): Flow<ResultStatus<List<Note>>>

    fun editNote(note: Note): Flow<ResultStatus<Unit>>

    fun deleteNote(note: Note): Flow<ResultStatus<Unit>>
}