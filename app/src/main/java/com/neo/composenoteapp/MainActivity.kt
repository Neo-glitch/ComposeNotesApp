package com.neo.composenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neo.composenoteapp.data.NotesDataSource
import com.neo.composenoteapp.model.Note
import com.neo.composenoteapp.screen.NoteScreen
import com.neo.composenoteapp.screen.NoteViewModel
import com.neo.composenoteapp.ui.theme.ComposeNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint   // needed inorder to dependencies using hilt here
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    val noteViewModel by viewModels<NoteViewModel>() // another way of viewmodel init , but only compose
                    val noteViewModel = viewModel<NoteViewModel>()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun NotesApp(
    noteViewModel: NoteViewModel = viewModel()
){
    // collect list as a state
    val notes = noteViewModel.notesList.collectAsState().value
    NoteScreen(notes = notes,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}

