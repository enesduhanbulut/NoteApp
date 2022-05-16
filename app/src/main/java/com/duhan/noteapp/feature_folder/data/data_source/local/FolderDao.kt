package com.duhan.noteapp.feature_folder.data.data_source.local

import androidx.room.*
import com.duhan.noteapp.feature_folder.domain.model.Folder
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

@Dao
interface FolderDao {
    @Query("SELECT * FROM folder")
    fun getAllFolders(): Observable<Folder>

    @Query("SELECT * FROM folder WHERE id = :id")
    fun getFolderById(id: Int): Maybe<Folder>

    @Query("DELETE FROM folder")
    fun deleteAllFolders(): Completable

    @Delete
    fun deleteFolder(folder: Folder): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFolder(folder: Folder): Completable

}