package com.duhan.noteapp.di

import android.app.Application
import androidx.room.Room
import com.duhan.noteapp.feature_note.data.data_source.local.NoteDatabase
import com.duhan.noteapp.feature_note.domain.repository.NoteRepository
import com.duhan.noteapp.feature_note.domain.repository.NoteRepositoryImpl
import com.duhan.noteapp.feature_note.domain.use_case.*
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

    @Provides
    @Singleton
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetNoteUseCase(repository: NoteRepository): GetNoteUseCase {
        return GetNoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateNoteUseCase(repository: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(repository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }
}