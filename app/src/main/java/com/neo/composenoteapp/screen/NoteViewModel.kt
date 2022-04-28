package com.neo.composenoteapp.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.neo.composenoteapp.data.NotesDataSource
import com.neo.composenoteapp.model.Note

class NoteViewModel: ViewModel() {

    // list whose state cne bemoni
    private var notesList = mutableStateListOf<Note>()

    init {
        notesList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note){
        notesList.add(note)
    }

    fun removeNote(note: Note){
        notesList.remove(note)
    }

    fun getAllNotes(): List<Note> = notesList
}