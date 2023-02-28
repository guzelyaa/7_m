package com.example.m7l1.domain.usecase

import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.repository.NoteRepository

class EditNoteUseCase(
    private val noteRepository: NoteRepository
) {
    fun editNote(note: Note) = noteRepository.editNote(note)
}