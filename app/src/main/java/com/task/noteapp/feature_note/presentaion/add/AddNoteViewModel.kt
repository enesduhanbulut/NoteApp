package com.task.noteapp.feature_note.presentaion.add

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.task.noteapp.feature_note.domain.use_case.ValidateUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(var addNoteUseCase: AddNoteUseCase) : ViewModel() {
    private var noteState: AddNoteState = AddNoteState()
    private var noteUIState: AddNoteUIState = AddNoteUIState()
    private val uiStateLiveData: MutableLiveData<AddNoteUIState> = MutableLiveData()
    private val validateUrlUseCase = ValidateUrlUseCase()
    fun firstTimeLoad() {
        uiStateLiveData.postValue(noteUIState)
    }

    fun onEvent(event: AddEvent) {
        when (event) {
            is AddEvent.EnteredHead -> {
                noteState.head = event.value
            }
            is AddEvent.SelectedColor -> {
                noteState.color = event.value.ordinal
                if (noteState.color != noteUIState.color) {
                    noteUIState.color = event.value.ordinal
                    uiStateLiveData.postValue(noteUIState)
                }
            }
            is AddEvent.EnteredBody -> {
                noteState.body = event.value
            }
            is AddEvent.EnteredURL -> {
                noteState.url = event.value
                validateUrlUseCase.execute(event.value).let { isValid ->
                    if (isValid) {
                        noteUIState.url = event.value
                        uiStateLiveData.postValue(noteUIState)
                    }
                }
            }

        }
    }

    fun uiStateLiveData(): LiveData<AddNoteUIState> {
        return uiStateLiveData
    }

    data class AddNoteState(
        var head: String = "",
        var body: String = "",
        var url: String = "",
        var color: Int = 0,
    )

    data class AddNoteUIState(
        var color: Int = 0,
        var url: String = "",
        var isUrlValid: Boolean = false,
        var image: Bitmap? = null,
        var date: String = Calendar.getInstance().time.toString()
    )

}