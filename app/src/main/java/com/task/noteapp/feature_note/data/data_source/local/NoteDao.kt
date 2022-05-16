package com.task.noteapp.feature_note.data.data_source.local

import androidx.room.*
import com.task.noteapp.feature_note.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Maybe<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Long): Maybe<Note>

    @Query("DELETE FROM note")
    suspend fun deleteAllNotes(): Completable

    @Delete
    suspend fun deleteNote(note: Note): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Completable

}