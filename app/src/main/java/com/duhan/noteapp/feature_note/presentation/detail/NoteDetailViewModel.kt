package com.duhan.noteapp.feature_note.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duhan.noteapp.feature_note.domain.FormattedDate
import com.duhan.noteapp.feature_note.domain.model.Note
import com.duhan.noteapp.feature_note.domain.use_case.*
import com.duhan.noteapp.feature_note.presentation.NoteColor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private var addNoteUseCase: AddNoteUseCase,
    private var getNoteUseCase: GetNoteUseCase,
    private var deleteNoteUseCase: DeleteNoteUseCase,
    private var updateNoteUseCase: UpdateNoteUseCase

) : ViewModel() {
    private var noteDetailState: NoteDetailState = NoteDetailState()
    private var noteDetailUiStateLiveData: MutableLiveData<NoteDetailUIEvent> = MutableLiveData()
    private val validateUrlUseCase = ValidateUrlUseCase()
    private var currentNote: Note? = null
    fun onEvent(noteDetailEvent: NoteDetailEvent) {
        when (noteDetailEvent) {
            is NoteDetailEvent.EnteredHead -> {
                this.noteDetailState.head = noteDetailEvent.value
            }
            is NoteDetailEvent.SelectedColor -> {
                onColorSelected(noteDetailEvent.value)
            }
            is NoteDetailEvent.EnteredBody -> {
                this.noteDetailState.body = noteDetailEvent.value
            }
            is NoteDetailEvent.EnteredURL -> {
                validateUrlAndSetImage(noteDetailEvent.value)
            }
            is NoteDetailEvent.ClickedSave -> {
                onSaveClicked()
            }
            is NoteDetailEvent.ClickedUpdate -> {
                onEditClicked()
            }
            is NoteDetailEvent.Init -> {
                onInit(noteDetailEvent)
            }
            is NoteDetailEvent.ClickedDelete -> {
                onDeleteClicked()
            }
            is NoteDetailEvent.DeleteNote -> {
                deleteApproved()
            }

        }
    }

    private fun deleteApproved() {
        deleteNoteUseCase.execute(currentNote!!)
            .subscribe({
                noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.Finish)
            }, {
                noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.ShowError(it.message.toString()))
            })
    }

    private fun onDeleteClicked() {
        noteDetailUiStateLiveData.postValue(
            NoteDetailUIEvent.DeleteNote
        )
    }

    private fun onInit(noteDetailEvent: NoteDetailEvent.Init) {
        this.noteDetailState.type = noteDetailEvent.type
        if (noteDetailEvent.type == ViewType.EDIT) {
            getNoteUseCase.execute(noteDetailEvent.id)
                .subscribe({
                    currentNote = it
                    noteDetailState.head = it.head
                    noteDetailState.body = it.body
                    noteDetailState.color = NoteColor.values()[it.color]
                    noteDetailState.url = it.url
                    noteDetailState.date = FormattedDate.formatDate(it.createDate)
                    noteDetailUiStateLiveData.postValue(
                        NoteDetailUIEvent.UpdateUI(noteDetailState)
                    )
                }, {
                    noteDetailUiStateLiveData.postValue(it.message?.let { it1 ->
                        NoteDetailUIEvent.ShowError(
                            it1
                        )
                    })
                })
        } else {
            noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.UpdateUI(noteDetailState))
        }
    }

    private fun onSaveClicked() {
        addNoteUseCase.execute(
            this.noteDetailState.head,
            this.noteDetailState.body,
            this.noteDetailState.color.ordinal,
            this.noteDetailState.url
        )
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.ShowMessage("Note Saved"))
                noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.Finish)
            }, {
                noteDetailUiStateLiveData.postValue(it.message?.let { it1 ->
                    NoteDetailUIEvent.ShowError(
                        it1
                    )
                })
            })
    }

    private fun onEditClicked() {
        currentNote!!.head = this.noteDetailState.head
        currentNote!!.body = this.noteDetailState.body
        currentNote!!.color = this.noteDetailState.color.ordinal
        currentNote!!.url = this.noteDetailState.url
        currentNote!!.isEdited = true
        currentNote!!.lastModifiedDate = Calendar.getInstance().time
        updateNoteUseCase.execute(
            currentNote!!
        )
            .subscribe({
                noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.ShowMessage("Note Updated"))
                noteDetailUiStateLiveData.postValue(NoteDetailUIEvent.Finish)
            }, {
                noteDetailUiStateLiveData.postValue(it.message?.let { it1 ->
                    NoteDetailUIEvent.ShowError(
                        it1
                    )
                })
            })
    }


    private fun onColorSelected(value: NoteColor) {
        if (this.noteDetailState.color != value) {
            this.noteDetailState.color = value
            noteDetailUiStateLiveData.postValue(
                NoteDetailUIEvent.UpdateUI(
                    noteDetailState
                )
            )
        }
    }

    private fun validateUrlAndSetImage(url: String) {
        validateUrlUseCase.execute(url).let { isValid ->
            if (isValid) {
                this.noteDetailState.url = url
                noteDetailUiStateLiveData.postValue(
                    NoteDetailUIEvent.ShowImage(
                        url
                    )
                )
            } else {
                noteDetailUiStateLiveData.postValue(
                    NoteDetailUIEvent.ClearImage(
                        url
                    )
                )
            }
        }
    }

    fun uiStateLiveData(): LiveData<NoteDetailUIEvent> {
        return noteDetailUiStateLiveData
    }

    data class NoteDetailState(
        var type: ViewType = ViewType.ADD,
        var head: String = "",
        var body: String = "",
        var url: String = "",
        var color: NoteColor = NoteColor.YELLOW,
        var date: String = FormattedDate.newInstance()
    )

}