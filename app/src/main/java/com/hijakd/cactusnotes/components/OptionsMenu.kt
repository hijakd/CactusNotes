package com.hijakd.cactusnotes.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.hijakd.cactusnotes.model.Note

@Composable
fun ShowOptionsMenu(modifier: Modifier = Modifier,
                    expandOptionsMenu: MutableState<Boolean>,
                    loadSampleNotesStatus: MutableState<Boolean>,
                    showSampleNotesDialogStatus: MutableState<Boolean>,
                    notesList: MutableList<Note>,
                    onDeleteAllNotes: (Note) -> Unit
) {

    DropdownMenu(expanded = expandOptionsMenu.value, onDismissRequest = { true }) {
        DropdownMenuItem(
            text = { Text(text = "Show Sample Notes Dialog", style = MaterialTheme.typography.bodyMedium) },
            onClick = {
                expandOptionsMenu.value = false
                showSampleNotesDialogStatus.value = true
            }
        )
        DropdownMenuItem(
            text = { Text(text = "Delete All Notes") },
            onClick = { onDeleteAllNotes }
        )
    }

    DefaultNotesDialog(
        modifier,
        loadSampleNotesStatus = loadSampleNotesStatus,
        defaultNotesDialogStatus = showSampleNotesDialogStatus,
        notesList = notesList
    )
}