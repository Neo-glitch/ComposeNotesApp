package com.neo.composenoteapp.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.composenoteapp.data.NotesDataSource
import com.neo.composenoteapp.model.Note
import com.neo.composenoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(val repository: NoteRepository)
    : ViewModel() {

    private val _notesList = MutableStateFlow<List<Note>>(emptyList())
    val notesList = _notesList.asStateFlow()  // similar to live data which is read only

    // mutableState list
//    private var notesList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes()
                .distinctUntilChanged()  // gets distinct data items in stream and filters duplicate
                .collect { listOfNotes ->
                    if(listOfNotes.isNullOrEmpty())
                        Log.d("Empty List", "Empty list")
                    else{
                        _notesList.value = listOfNotes
                    }
                }
        }
//        notesList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note){
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun removeNote(note: Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }


}