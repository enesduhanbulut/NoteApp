package com.task.noteapp.feature_note.presentaion.list

import com.task.noteapp.feature_note.domain.model.Note

sealed class ListUIEvent {
    data class ShowNote(val value: Note): ListUIEvent()
    data class UpdateList(val value: List<Note>): ListUIEvent()
    data class ShowMessage(val message: String): ListUIEvent()
    data class ShowError(val message: String): ListUIEvent()
}