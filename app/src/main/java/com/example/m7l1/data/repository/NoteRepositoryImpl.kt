package com.example.m7l1.data.repository

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
) : NoteRepository {
    override fun createNote(note: Note): Flow<ResultStatus<Unit>> = flow{
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.createNote(note.totNoteEntity())
            emit(ResultStatus.Success(data))
        }catch (e:IOException){
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllNotes(): Flow<ResultStatus<List<Note>>> = flow {
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.getAllNotes().map { it.toNote() }
            emit(ResultStatus.Success(data))
        }catch (e:IOException){
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun editNote(note: Note): Flow<ResultStatus<Unit>> = flow{
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.editNote(note.totNoteEntity())
            emit(ResultStatus.Success(data))
        }catch (e:IOException){
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteNote(note: Note): Flow<ResultStatus<Unit>> = flow{
        emit(ResultStatus.Loading())
        try {
            val data = noteDao.deleteNote(note.totNoteEntity())
            emit(ResultStatus.Success(data))
        }catch (e:IOException){
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}