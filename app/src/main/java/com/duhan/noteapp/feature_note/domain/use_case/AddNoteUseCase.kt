package com.duhan.noteapp.feature_note.domain.use_case

import com.duhan.noteapp.feature_note.domain.model.Note
import com.duhan.noteapp.feature_note.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import java.lang.IllegalStateException

class AddNoteUseCase(var repository: NoteRepository) {
    fun execute(head: String, title: String, color: Int, imageUrl: String): Completable {
        if (head.isEmpty() || title.isEmpty()) {
            return Completable.error(IllegalStateException("Title and Content can't be empty"))
        }
        return repository.saveNote(Note(head, title, color, imageUrl))
    }
}


