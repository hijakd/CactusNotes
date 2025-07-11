package com.hijakd.cactusnotes.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hijakd.cactusnotes.model.Note

@Composable
fun StateVars() {

    val menuStatus = remember { mutableStateOf(false) }

    val showSampleNotesDialog = remember { mutableStateOf(true) }

    val doLoadSampleNotes = remember { mutableStateOf(false) }

    val mutableNotes = remember { mutableListOf<Note>() }
}