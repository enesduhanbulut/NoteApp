package com.duhan.noteapp.feature_folder.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duhan.noteapp.feature_folder.domain.model.Folder

@Database(entities = [Folder::class], version = 1)
abstract class FolderDatabase : RoomDatabase() {
    abstract fun folderDao(): FolderDao
}