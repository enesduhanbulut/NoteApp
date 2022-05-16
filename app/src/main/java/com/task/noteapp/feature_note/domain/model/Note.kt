package com.task.noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    val head: String,
    val body: String,
    val color: Int,
    val url: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var folderId: Long = 0
    var lastModifiedData: Date = Date()
    var createDate: Date = Date()
    var isFavorite: Boolean = false
    var isTrash: Boolean = false
    var isCompleted: Boolean = false

}
