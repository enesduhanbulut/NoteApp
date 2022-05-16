package com.task.noteapp.feature_note.domain.repository

import com.task.noteapp.feature_note.data.data_source.local.NoteDao
import com.task.noteapp.feature_note.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getAllNotes(): Observable<Note> {
        return noteDao.getAllNotes();
    }

    override fun getNoteById(id: Long): Maybe<Note> {
        return noteDao.getNoteById(id);
    }

    override fun saveNote(note: Note): Completable {
        return noteDao.insertNote(note);
    }

    override fun deleteNote(note: Note): Completable {
        return noteDao.deleteNote(note);
    }

    override fun deleteAllNotes(): Completable {
        return noteDao.deleteAllNotes();
    }
}