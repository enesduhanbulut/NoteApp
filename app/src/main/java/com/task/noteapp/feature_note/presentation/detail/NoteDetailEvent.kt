package com.task.noteapp.feature_note.presentation.detail

import com.task.noteapp.feature_note.presentation.NoteColor


sealed class NoteDetailEvent {
    data class Init(val type: ViewType, val id: Int) : NoteDetailEvent()
    data class EnteredHead(val value: String) : NoteDetailEvent()
    data class EnteredBody(val value: String) : NoteDetailEvent()
    data class SelectedColor(val value: NoteColor) : NoteDetailEvent()
    data class EnteredURL(val value: String) : NoteDetailEvent()
    object ClickedSave : NoteDetailEvent()
    object ClickedUpdate : NoteDetailEvent()
}

