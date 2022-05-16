package com.task.noteapp.feature_note.presentaion.list

import com.task.noteapp.feature_note.domain.model.Note

sealed class ListEvent {
    data class NoteSelected(val value: Note) : ListEvent()
    data class GetNotes(val temp :Boolean) : ListEvent()
}