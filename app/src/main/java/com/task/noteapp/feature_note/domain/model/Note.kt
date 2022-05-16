package com.task.noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    @PrimaryKey val id: Long,
    val head: String,
    val body: String,
    val lastModifiedData: Date,
    val createDate: Date,
    val isFavorite: Boolean,
    val isArchived: Boolean,
    val color: Int
)
