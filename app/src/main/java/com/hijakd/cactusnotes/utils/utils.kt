package com.hijakd.cactusnotes.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE. d MMM y - HH:mm", Locale.getDefault())
    return format.format(date)
}

fun formatDate(time: Date): String {
    val format = SimpleDateFormat("EEE. d MMM y - HH:mm", Locale.getDefault())
    return format.format(time)
}

@Composable
fun checkTheme(darkModeColor: Color, lightModeColor: Color){
    if (isSystemInDarkTheme()) darkModeColor else lightModeColor
}

@Composable
fun getHalfWidth(): Dp {
    val screenSize = LocalWindowInfo.current.containerSize
    val screenDensity = LocalDensity.current.density
    return ((screenSize.width / 2) / screenDensity).dp
}

/* TODO: this may not be functioning as expected */
fun findNoteById(noteId: String?, notesList: MutableList<Note>): SnapshotStateList<Note> {
    val note: Note = notesList[findNoteIndex(noteId, notesList)]
    return note
//    return notesList.first { note ->
//        note.id.toString() == noteId
//    }
}

fun findNoteIndex(noteId: String?, notesList: List<Note>): Int{
    var index = 0
    for (note in notesList){
        if (note.id.toString() != noteId){
            index++
        }
    }

    return index
}

fun loadNotesList(mutableNotesList: MutableList<Note>){
    var idx = 0


}