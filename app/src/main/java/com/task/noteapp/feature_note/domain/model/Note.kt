package com.task.noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    var head: String,
    var body: String,
    var color: Int,
    var url: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var folderId: Int = 0
    var lastModifiedDate: Date = Date()
    var isEdited = false
    var createDate: Date = Date()
    var isFavorite: Boolean = false
    var isTrash: Boolean = false
    var isCompleted: Boolean = false

}
