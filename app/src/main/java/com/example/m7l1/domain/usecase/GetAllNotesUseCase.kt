package com.example.m7l1.domain.usecase

import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.repository.NoteRepository
import com.example.m7l1.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun getAllNotes():Flow<ResultStatus<List<Note>>>{
        return noteRepository.getAllNotes()
    }

}