package com.hijakd.cactusnotes.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.components.DefaultNotesDialog
import com.hijakd.cactusnotes.components.NavDropDownMenu
import com.hijakd.cactusnotes.components.NoteCard
import com.hijakd.cactusnotes.database.DummyNotes
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.navigation.ScreenRoutes
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.utils.Vars


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
        modifier: Modifier = Modifier,
        notesList: MutableList<Note>,
        editableNoteId: MutableState<String>,
        menuStatus: MutableState<Boolean>,
        expandOptionsMenu: MutableState<Boolean>,
        navController: NavHostController,
        showSampleNotesDialogStatus: MutableState<Boolean>,
        doLoadSampleNotes: MutableState<Boolean>,
        onRemoveNote: (Note) -> Unit,
        onDeleteAllNotes: (Note) -> Unit = {},
        onAddNote: (Note) -> Unit,
) {

    var canAddNewNote by remember { mutableStateOf(false) }

    var noteItem: Note
    val TAG = "dialog"

    Log.d(TAG, "NotesScreen: early notesList size: ${notesList.size}")


    if (showSampleNotesDialogStatus.value && Vars.firstload == 0) {
//        Toast.makeText(LocalContext.current, "showing dialog", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "NotesScreen: showing dialog")
        DefaultNotesDialog(
            modifier,
            loadSampleNotesStatus = doLoadSampleNotes,
            defaultNotesDialogStatus = showSampleNotesDialogStatus,
            notesList = notesList
        )
        Vars.firstload = 1
    }

    if (doLoadSampleNotes.value) {
        for (notes in notesList) {
            onAddNote(Note(title = notes.title, body = notes.body, category = notes.category))
        }
    }

    Scaffold(modifier.fillMaxSize(), topBar = {

        TopAppBar(
            title = { Text("Notes") },
            navigationIcon = {
                IconButton(onClick = { menuStatus.value = true }) {
                    Icon(
                        Icons.Rounded.Menu,
                        modifier = modifier
                                .padding(horizontal = 10.dp),
                        contentDescription = "menu icon",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    NavDropDownMenu(menuStatus, navController)
                }
            },
            actions = {
                /* New note button */
                IconButton(onClick = { navController.navigate(route = ScreenRoutes.NewNoteScreen.name) }) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "add new item icon",
                        modifier
                                .padding(end = 15.dp)
                                .size(37.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                /* Options dropdown menu */
                IconButton(onClick = {expandOptionsMenu.value = true}) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "category icon",
                        modifier
                                .padding(end = 15.dp)
                                .size(30.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

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
                        onClick = {
                            expandOptionsMenu.value = false

//                            notesList.forEach { onRemoveNote(it) }

                        }
                    )
                }
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }) {
        Column(
            modifier
                    .padding(it)
                    .fillMaxSize()
            /*.background(LightGrey)*/,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.d(TAG, "NotesScreen: late notesList size: ${notesList.size}")

            Spacer(modifier = Modifier.size(7.dp))

            /*LazyColumn(modifier) {
                items(count = notesList.distinctBy { thisItem ->
                    thisItem.title }.count(), itemContent = { item ->
                    noteItem = notesList[item]
                    NoteCard(modifier, noteItem, editableNoteId, navController, onRemoveNote = {onRemoveNote(noteItem)})
                })
            }*/
            LazyColumn(modifier) {
                items(count = notesList.count(), itemContent = { item ->
                    noteItem = notesList[item]
                    NoteCard(modifier, noteItem, editableNoteId, navController, onRemoveNote = {onRemoveNote(noteItem)})
                })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowNotesScreen() {
    val navController = rememberNavController()
    val menuStatus = remember { mutableStateOf(false) }
    val expandOptions = remember { mutableStateOf(false) }
    val loadSamples = remember { mutableStateOf(true) }
    val mutableNotes = remember { mutableListOf<Note>() }
    val noteId = remember { mutableStateOf("") }

    for (notes in DummyNotes().loadNotes()) {
        mutableNotes.add(notes)
    }
    CactusNotesTheme {
        NotesScreen(
            notesList = mutableNotes,
            editableNoteId = noteId,
            menuStatus = menuStatus,
            expandOptionsMenu = expandOptions,
            navController = navController,
            showSampleNotesDialogStatus = loadSamples,
            doLoadSampleNotes = loadSamples,
            onRemoveNote = {},
            onAddNote = {},
        )
    }
}