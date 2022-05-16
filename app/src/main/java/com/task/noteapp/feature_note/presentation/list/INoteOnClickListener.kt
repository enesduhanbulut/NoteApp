package com.task.noteapp.feature_note.presentation.list

import com.task.noteapp.feature_note.domain.model.Note

interface INoteOnClickListener {
    fun onClick(note: Note)
}