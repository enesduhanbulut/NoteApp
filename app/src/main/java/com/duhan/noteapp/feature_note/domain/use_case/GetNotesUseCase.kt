package com.duhan.noteapp.feature_note.domain.use_case

import com.duhan.noteapp.feature_note.domain.model.Note
import com.duhan.noteapp.feature_note.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(): Maybe<List<Note>> {
        return noteRepository.getAllNotes()
            .subscribeOn(Schedulers.io())
    }
}