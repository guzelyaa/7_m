package com.example.m7l1.domain.usecase

import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke(note: Note) = noteRepository.deleteNote(note)
}