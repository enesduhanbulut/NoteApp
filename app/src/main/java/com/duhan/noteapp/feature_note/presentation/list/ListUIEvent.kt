package com.duhan.noteapp.feature_note.presentation.list

import com.duhan.noteapp.feature_note.domain.model.Note

sealed class ListUIEvent {
    data class ShowNote(val value: Note): ListUIEvent()
    data class UpdateList(val value: List<Note>): ListUIEvent()
    data class ShowMessage(val message: String): ListUIEvent()
    data class ShowError(val message: String): ListUIEvent()
}