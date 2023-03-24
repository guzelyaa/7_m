package com.example.m7l1.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m7l1.domain.model.Note
import com.example.m7l1.domain.usecase.CreateNoteUseCase
import com.example.m7l1.domain.usecase.DeleteNoteUseCase
import com.example.m7l1.domain.usecase.GetAllNotesUseCase
import com.example.m7l1.domain.utils.ResultStatus
import com.example.m7l1.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    private val _notesState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val noteState = _notesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val deleteNoteState = _deleteNoteState.asStateFlow()

    private val _createNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    fun getAllNotes() {
        getAllNotesUseCase().collectFlow(_notesState)
    }

    init {
        getAllNotes()
    }

    fun delete(note: Note) {
        deleteNoteUseCase(note).collectFlow(_deleteNoteState)
    }

    fun delete(position: Int, note: Note) {
        if (note.title.isNotBlank() && note.description.isNotBlank() && position != -1) {
            deleteNoteUseCase(note).collectFlow(_deleteNoteState)
        } else {
            _deleteNoteState.value =
                UiState.Error(msg = "You are trying to delete sth that does not exist.")
        }
    }


}