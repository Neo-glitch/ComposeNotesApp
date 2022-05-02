package com.neo.composenoteapp.repository

import com.neo.composenoteapp.data.NoteDatabaseDao
import com.neo.composenoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// injects notedb dao here
class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    suspend fun update(note: Note) = noteDatabaseDao.update(note)
    suspend fun delete(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes(note: Note) = noteDatabaseDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO)
        .conflate()
}