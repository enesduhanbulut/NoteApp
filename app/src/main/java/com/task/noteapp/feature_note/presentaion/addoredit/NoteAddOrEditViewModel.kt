package com.task.noteapp.feature_note.presentaion.addoredit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.task.noteapp.feature_note.domain.use_case.ValidateUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteAddOrEditViewModel @Inject constructor(var addNoteUseCase: AddNoteUseCase) : ViewModel() {
    private var noteState: AddNoteState = AddNoteState()
    private val addOrEditUiStateLiveData: MutableLiveData<AddOrEditUIEvent> = MutableLiveData()
    private val validateUrlUseCase = ValidateUrlUseCase()
    fun firstTimeLoad() {
        addOrEditUiStateLiveData.postValue(AddOrEditUIEvent.UpdateTime(Calendar.getInstance().time.toString()))
    }

    fun onEvent(addOrEditEvent: AddOrEditEvent) {
        when (addOrEditEvent) {
            is AddOrEditEvent.EnteredHead -> {
                noteState.head = addOrEditEvent.value
            }
            is AddOrEditEvent.SelectedColor -> {
                if (noteState.color != addOrEditEvent.value.ordinal) {
                    noteState.color = addOrEditEvent.value.ordinal
                    addOrEditUiStateLiveData.postValue(AddOrEditUIEvent.UpdateColor(addOrEditEvent.value))
                }
            }
            is AddOrEditEvent.EnteredBody -> {
                noteState.body = addOrEditEvent.value
            }
            is AddOrEditEvent.EnteredURL -> {
                validateUrlUseCase.execute(addOrEditEvent.value).let { isValid ->
                    if (isValid) {
                        noteState.url = addOrEditEvent.value
                        addOrEditUiStateLiveData.postValue(AddOrEditUIEvent.ShowImage(addOrEditEvent.value))
                    } else {
                        addOrEditUiStateLiveData.postValue(
                            AddOrEditUIEvent.ClearImage(
                                addOrEditEvent.value
                            )
                        )
                    }
                }
            }
            is AddOrEditEvent.ClickedSave -> {
                addNoteUseCase.execute(
                    noteState.head,
                    noteState.body,
                    noteState.color,
                    noteState.url
                )
                    .subscribe({
                        addOrEditUiStateLiveData.postValue(AddOrEditUIEvent.ShowMessage("Note Saved"))
                    }, {
                        addOrEditUiStateLiveData.postValue(it.message?.let { it1 ->
                            AddOrEditUIEvent.ShowError(
                                it1
                            )
                        })
                    })
            }

        }
    }

    fun uiStateLiveData(): LiveData<AddOrEditUIEvent> {
        return addOrEditUiStateLiveData
    }

    data class AddNoteState(
        var head: String = "",
        var body: String = "",
        var url: String = "",
        var color: Int = 0,
    )

}