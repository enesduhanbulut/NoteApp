package com.task.noteapp.feature_note.domain.repository

import com.task.noteapp.feature_note.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

interface NoteRepository {
    fun getAllNotes(): Maybe<List<Note>>
    fun getNoteById(id: Int): Maybe<Note>
    fun saveNote(note: Note): Completable
    fun updateNote(note: Note): Completable
    fun deleteNote(note: Note): Completable
    fun deleteAllNotes(): Completable
}