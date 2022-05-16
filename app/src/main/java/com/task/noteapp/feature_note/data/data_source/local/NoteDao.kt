package com.task.noteapp.feature_note.data.data_source.local

import androidx.room.*
import com.task.noteapp.feature_note.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Maybe<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id: Long): Maybe<Note>

    @Query("DELETE FROM note")
    fun deleteAllNotes(): Completable

    @Delete
    fun deleteNote(note: Note): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note): Completable

}