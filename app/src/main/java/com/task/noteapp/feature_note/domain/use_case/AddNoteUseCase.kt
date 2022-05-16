package com.task.noteapp.feature_note.domain.use_case

import com.task.noteapp.feature_note.domain.repository.NoteRepository
import com.task.noteapp.feature_note.presentaion.add.NoteColor
import io.reactivex.rxjava3.core.Completable

class AddNoteUseCase(var repository: NoteRepository) {
    fun execute(head: String, title: String, color: NoteColor, imageUrl: String): Completable {
        return Completable.fromAction(null)
    }
}


