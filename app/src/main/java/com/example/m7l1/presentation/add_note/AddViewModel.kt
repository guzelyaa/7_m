package com.example.m7l1.presentation.add_note

import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.usecase.CreateNoteUseCase
import com.example.m7l1.domain.usecase.EditNoteUseCase
import com.example.m7l1.presentation.base.BaseViewModel
import com.example.m7l1.presentation.notes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun create(title: String, desc: String) {
        if (title.isNotEmpty() && title.isNotBlank()) {
            createNoteUseCase(
                Note(
                    title = title,
                    description = desc,
                    createAt = System.currentTimeMillis()
                )
            ).collectFlow(_createNoteState)
        } else {
            _createNoteState.value = UiState.Error("Title is empty!")
        }
    }

    fun update(note: Note) {
        if (note.title.isNotEmpty() && note.title.isNotBlank()) {
            editNoteUseCase(note).collectFlow(_editNoteState)
        } else {
            _editNoteState.value = UiState.Error("Title is empty!")
        }
    }

}