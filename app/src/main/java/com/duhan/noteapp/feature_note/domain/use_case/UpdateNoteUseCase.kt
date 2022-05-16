package com.duhan.noteapp.feature_note.domain.use_case

import com.duhan.noteapp.feature_note.domain.model.Note
import com.duhan.noteapp.feature_note.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class UpdateNoteUseCase(private val repository: NoteRepository) {
    fun execute(note: Note): Completable {
        if (note.head.isEmpty() || note.body.isEmpty()) {
            return Completable.error(Throwable("Title and Content can't be empty"))
        }
        return repository.updateNote(note)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }
}