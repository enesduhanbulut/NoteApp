package com.task.noteapp.feature_note.presentaion.add

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
    private val uiStateLiveData: MutableLiveData<UIEvent> = MutableLiveData()
    private val validateUrlUseCase = ValidateUrlUseCase()
    fun firstTimeLoad() {
        uiStateLiveData.postValue(UIEvent.UpdateTime(Calendar.getInstance().time.toString()))
    }

    fun onEvent(event: AddEvent) {
        when (event) {
            is AddEvent.EnteredHead -> {
                noteState.head = event.value
            }
            is AddEvent.SelectedColor -> {
                if (noteState.color != event.value.ordinal) {
                    noteState.color = event.value.ordinal
                    uiStateLiveData.postValue(UIEvent.UpdateColor(event.value))
                }
            }
            is AddEvent.EnteredBody -> {
                noteState.body = event.value
            }
            is AddEvent.EnteredURL -> {
                validateUrlUseCase.execute(event.value).let { isValid ->
                    if (isValid) {
                        noteState.url = event.value
                        uiStateLiveData.postValue(UIEvent.ShowImage(event.value))
                    } else {
                        uiStateLiveData.postValue(UIEvent.ClearImage(event.value))
                    }
                }
            } is AddEvent.ClickedSave -> {
                addNoteUseCase.execute(noteState.head, noteState.body, noteState.color, noteState.url)
                    .subscribe( {
                        uiStateLiveData.postValue(UIEvent.ShowMessage("Note Saved"))
                    }, {
                        uiStateLiveData.postValue(it.message?.let { it1 -> UIEvent.ShowError(it1) })
                    })
            }

        }
    }

    fun uiStateLiveData(): LiveData<UIEvent> {
        return uiStateLiveData
    }

    data class AddNoteState(
        var head: String = "",
        var body: String = "",
        var url: String = "",
        var color: Int = 0,
    )

}