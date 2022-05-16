package com.task.noteapp.feature_note.presentaion.add


sealed class AddEvent{
    data class EnteredHead(val value: String): AddEvent()
    data class EnteredBody(val value: String): AddEvent()
    data class SelectedColor(val value: NoteColor): AddEvent()
    data class EnteredURL(val value: String): AddEvent()
    object SaveNote: AddEvent()
}

