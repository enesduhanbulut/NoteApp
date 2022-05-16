package com.task.noteapp.feature_note.presentaion.addoredit


sealed class AddOrEditUIEvent{
    data class ShowImage(val value: String): AddOrEditUIEvent()
    data class ClearImage(val value: String): AddOrEditUIEvent()
    data class UpdateColor(val color: NoteColor): AddOrEditUIEvent()
    data class UpdateTime(val value: String): AddOrEditUIEvent()
    data class ShowError(val message: String): AddOrEditUIEvent()
    data class ShowMessage(val message: String) : AddOrEditUIEvent()
}

