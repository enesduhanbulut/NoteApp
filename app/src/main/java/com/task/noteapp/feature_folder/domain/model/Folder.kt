package com.task.noteapp.feature_folder.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder")
data class Folder(
    @PrimaryKey val id: Int,
    val title: String
)
