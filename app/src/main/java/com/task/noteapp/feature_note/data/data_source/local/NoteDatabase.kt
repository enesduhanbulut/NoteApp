package com.task.noteapp.feature_note.data.data_source.local

import androidx.room.Database
import androidx.room.TypeConverters
import com.task.noteapp.feature_note.domain.model.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase {
    abstract fun noteDao(): NoteDao
}