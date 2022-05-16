package com.task.noteapp.feature_note.domain.use_case

import com.task.noteapp.feature_note.domain.model.Note
import com.task.noteapp.feature_note.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Observable

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    fun getNotes(): Observable<Note> {
        return noteRepository.getAllNotes()
    }
}