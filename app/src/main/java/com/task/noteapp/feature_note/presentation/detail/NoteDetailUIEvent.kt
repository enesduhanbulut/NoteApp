package com.task.noteapp.feature_note.presentation.detail


sealed class NoteDetailUIEvent{
    data class ShowImage(val value: String): NoteDetailUIEvent()
    data class ClearImage(val value: String): NoteDetailUIEvent()
    data class ShowError(val message: String): NoteDetailUIEvent()
    data class ShowMessage(val message: String) : NoteDetailUIEvent()
    data class UpdateUI(val value: NoteDetailViewModel.NoteDetailState) : NoteDetailUIEvent()
}

