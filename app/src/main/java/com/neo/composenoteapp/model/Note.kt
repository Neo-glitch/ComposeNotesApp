package com.neo.composenoteapp.model

import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(), // for each new note
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()  // timestamp
)