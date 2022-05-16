package com.duhan.noteapp.feature_folder.domain.repository

import com.duhan.noteapp.feature_folder.domain.model.Folder
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface FolderRepository {
    fun getAllFolders(): Maybe<Folder>
    fun getFolderById(id: Int): Maybe<Folder>
    fun createFolder(folder: Folder): Completable
    fun deleteFolder(folder: Folder): Completable
    fun deleteAllFolders(): Completable
}