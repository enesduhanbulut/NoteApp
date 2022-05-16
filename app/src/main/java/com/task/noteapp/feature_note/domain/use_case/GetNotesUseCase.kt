package com.task.noteapp.feature_note.domain.use_case

import com.task.noteapp.feature_note.domain.model.Note
import com.task.noteapp.feature_note.domain.repository.NoteRepository
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(): Maybe<List<Note>> {
        return noteRepository.getAllNotes()
            .subscribeOn(Schedulers.io())
    }
}