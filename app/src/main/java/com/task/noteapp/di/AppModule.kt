package com.task.noteapp.di

import android.app.Application
import androidx.room.Room
import com.task.noteapp.feature_note.data.data_source.local.NoteDatabase
import com.task.noteapp.feature_note.domain.repository.NoteRepository
import com.task.noteapp.feature_note.domain.repository.NoteRepositoryImpl
import com.task.noteapp.feature_note.domain.use_case.AddNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideAddUseCase(repository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }
}