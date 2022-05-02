package com.neo.composenoteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neo.composenoteapp.data.NoteDatabase
import com.neo.composenoteapp.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// module for providing bindings or how hilt should get objects

@InstallIn(SingletonComponent::class)  // needed for singleton provider (useful for room db instance)
@Module
object AppModule {

    // provider
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao =
        noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()


}