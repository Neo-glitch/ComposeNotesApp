package com.neo.composenoteapp.data

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.neo.composenoteapp.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {


    @Query("SELECT * from notes_table")
    fun getNotes(): Flow<List<Note>>  // no need for suspend, since already done asyncronously

    @Query("SELECT * from notes_table where id=:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}