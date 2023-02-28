package com.example.m7l1.data

import com.example.m7l1.data.model.NoteEntity
import com.example.m7l1.domain.model.Note

fun Note.totNoteEntity() = NoteEntity(
    id, title, description, createAt
)

fun NoteEntity.toNote() = Note(
    id, title, description, createAt
)