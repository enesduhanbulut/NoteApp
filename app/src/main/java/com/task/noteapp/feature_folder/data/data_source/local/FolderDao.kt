package com.task.noteapp.feature_folder.data.data_source.local

import androidx.room.*
import com.task.noteapp.feature_folder.domain.model.Folder
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface FolderDao {
    @Query("SELECT * FROM folder")
    fun getAllFolders(): Maybe<Folder>

    @Query("SELECT * FROM folder WHERE id = :id")
    suspend fun getFolderById(id: Long): Maybe<Folder>

    @Query("DELETE FROM folder")
    suspend fun deleteAllFolders(): Completable

    @Delete
    suspend fun deleteFolder(folder: Folder): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: Folder): Completable

}