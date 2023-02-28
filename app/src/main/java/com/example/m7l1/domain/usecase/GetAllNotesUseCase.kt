package com.example.m7l1.domain.usecase

import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.repository.NoteRepository

class GetAllNotesUseCase(
    private val noteRepository: NoteRepository
) {
    fun getAllNotes() = noteRepository.getAllNotes()
}