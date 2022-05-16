package com.duhan.noteapp.feature_note.domain.use_case

import com.duhan.noteapp.feature_note.domain.model.Note
import com.duhan.noteapp.feature_note.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class DeleteNoteUseCase(var repository: NoteRepository) {
    fun execute(note: Note): Completable {
        return repository.deleteNote(note)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }
}