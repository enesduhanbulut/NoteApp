package com.duhan.noteapp.feature_note.presentation.list

import com.duhan.noteapp.feature_note.domain.model.Note

sealed class ListEvent {
    data class NoteSelected(val value: Note) : ListEvent()
    data class GetNotes(val temp :Boolean) : ListEvent()
}