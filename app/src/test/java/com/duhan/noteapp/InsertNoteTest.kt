package com.duhan.noteapp

import com.duhan.noteapp.feature_note.domain.repository.NoteRepository
import com.duhan.noteapp.feature_note.domain.use_case.AddNoteUseCase
import io.reactivex.rxjava3.core.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any


@RunWith(MockitoJUnitRunner::class)
class InsertNoteTest {

    @Mock
    lateinit var noteRepository: NoteRepository

    private lateinit var insetNoteUseCase: AddNoteUseCase

    @Before
    fun setUp() {
        Mockito.doReturn(Completable.complete())
            .`when`(noteRepository).saveNote(any())
        insetNoteUseCase = AddNoteUseCase(noteRepository)
    }

    @Test
    fun cant_insert_note_when_head_is_empty() {
        insetNoteUseCase.execute("", "title", 1, "url")
            .test()
            .assertFailure(IllegalStateException::class.java)
    }


    @Test
    fun can_insert_note_when_head_and_title_not_empty() {
        insetNoteUseCase.execute("asd", "title", 1, "url")
            .test()
            .assertNoErrors()
            .assertComplete()
    }

}