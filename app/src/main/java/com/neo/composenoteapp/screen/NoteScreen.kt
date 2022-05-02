package com.neo.composenoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neo.composenoteapp.R
import com.neo.composenoteapp.components.NoteButton
import com.neo.composenoteapp.components.NoteInputText
import com.neo.composenoteapp.data.NotesDataSource
import com.neo.composenoteapp.model.Note
import com.neo.composenoteapp.util.formatDate
import java.time.format.DateTimeFormatter


/**
 * Main App Screen
 */
@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note> = mutableListOf(),
    onAddNote: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context= LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon notification"
                )
            },
            backgroundColor = Color(0xFFDADFE3)
        )

        // content of screen
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title, label = "Title",
                onTextChanged = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() })
                        title = it
                })

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description, label = "Description",
                onTextChanged = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() })
                        description = it
                })

            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // saved to list
                    onAddNote(Note(title = title, description = description))

                    title = ""
                    description = ""

                    Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
                }
            })
        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(it)
                })
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(4.dp)
            // clips content of surface to have this msg app msg design
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )

            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = formatDate(note.entryDate.time)
                , style = MaterialTheme.typography.caption
            )
        }
    }
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes())
}