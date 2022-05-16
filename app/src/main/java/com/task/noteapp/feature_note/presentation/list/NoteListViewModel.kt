package com.task.noteapp.feature_note.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.noteapp.feature_note.domain.model.Note
import com.task.noteapp.feature_note.domain.use_case.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(var getNoteUseCase: GetNotesUseCase) : ViewModel() {
    private var noteState: GetNoteState = GetNoteState()
    private val listStateLiveData: MutableLiveData<ListUIEvent> = MutableLiveData()

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.NoteSelected -> {
                noteState.selectedNote = event.value
                listStateLiveData.postValue(ListUIEvent.ShowNote(event.value))
                onCleared()
            }
            is ListEvent.GetNotes -> {
                getNoteUseCase.execute()
                    .subscribe({
                        noteState.notes = it
                        listStateLiveData.postValue(ListUIEvent.UpdateList(it))
                    }, {
                        listStateLiveData.postValue(ListUIEvent.ShowError(it.toString()))
                    })
            }
        }
    }


    fun uiStateLiveData(): LiveData<ListUIEvent> {
        return listStateLiveData
    }

    data class GetNoteState(
        var notes: List<Note>? = null,
        var selectedNote: Note? = null,
    )

}
