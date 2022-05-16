package com.task.noteapp.feature_note.presentaion.addoredit


sealed class AddOrEditEvent{
    data class Type(val type: ViewType): AddOrEditEvent()
    data class EnteredHead(val value: String): AddOrEditEvent()
    data class EnteredBody(val value: String): AddOrEditEvent()
    data class SelectedColor(val value: NoteColor): AddOrEditEvent()
    data class EnteredURL(val value: String): AddOrEditEvent()
    object ClickedSave: AddOrEditEvent()
}

