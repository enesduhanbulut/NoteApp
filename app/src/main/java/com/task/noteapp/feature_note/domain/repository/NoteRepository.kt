package com.task.noteapp.feature_note.domain.repository

import com.task.noteapp.feature_note.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface NoteRepository {
    fun getAllNotes(): Maybe<Note>
    fun getNoteById(id: Long): Maybe<Note>
    fun saveNote(note: Note): Completable
    fun deleteNote(note: Note): Completable
    fun deleteAllNotes(): Completable
}