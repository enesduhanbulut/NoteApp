package com.task.noteapp.feature_note.presentaion.add

import androidx.lifecycle.ViewModel
import com.task.noteapp.feature_note.domain.use_case.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(var addNoteUseCase: AddNoteUseCase) : ViewModel() {

    private var noteState: AddNoteState = AddNoteState()

    fun onEvent(event: AddEvent) {
        when (event) {
            is AddEvent.EnteredHead -> {
                noteState.head = event.value
            }
            is AddEvent.SelectedColor -> {
                noteState.color = event.value
            }
            is AddEvent.EnteredBody -> {
                noteState.body = event.value
            }
            is AddEvent.EnteredURL -> {
                noteState.url = event.value
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

    data class AddNoteState(
        var head: String = "",
        var body: String = "",
        var url: String = "",
        var color: NoteColor = NoteColor.YELLOW
    )

}