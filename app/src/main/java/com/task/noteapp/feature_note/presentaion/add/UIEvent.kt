package com.task.noteapp.feature_note.presentaion.add


sealed class UIEvent{
    data class ShowImage(val value: String): UIEvent()
    data class ClearImage(val value: String): UIEvent()
    data class UpdateColor(val color: NoteColor): UIEvent()
    data class UpdateTime(val value: String): UIEvent()
    data class ShowError(val message: String): UIEvent()
    data class ShowMessage(val message: String) : UIEvent() {
    }
}

