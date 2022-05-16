package com.task.noteapp.feature_folder.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.task.noteapp.feature_note.domain.model.Note

data class FolderWithNotes(
    @Embedded val folder: Folder,
    @Relation(
        parentColumn = "id",
        entityColumn = "folderId"
    )
    val notes: List<Note>
)
