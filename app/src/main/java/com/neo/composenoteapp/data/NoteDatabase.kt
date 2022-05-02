package com.neo.composenoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.neo.composenoteapp.model.Note
import com.neo.composenoteapp.util.DateConverter
import com.neo.composenoteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao() : NoteDatabaseDao

}