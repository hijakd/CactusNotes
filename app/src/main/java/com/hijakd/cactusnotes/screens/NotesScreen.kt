package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.hijakd.cactusnotes.components.NoteCard
import com.hijakd.cactusnotes.components.TopBar
import com.hijakd.cactusnotes.database.DummyNotes
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier,
                notesList: List<Note>,
                menuStatus: MutableState<Boolean>,
                navController: NavHostController) {

//    val navController = rememberNavController()

//    var noteItem: Note
    val dummyNotes: List<Note> = DummyNotes().loadNotes()

    val dropMenuItemSelected = remember { mutableStateOf(false) }
    val category = remember { mutableStateOf("") }

    var canAddNewNote by remember { mutableStateOf(false) }
    var expandDropDown by remember { mutableStateOf(false) }

//    var title by remember { mutableStateOf("") }
//    var body by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopBar(
            modifier,
            title = "Notes",
            menuStatus = menuStatus,
            navController = navController,
            saveIcon = false,
            editNote = false,
            dropMenuItemSelected,
            category
        )
    }) {
        Column(
            modifier
                    .padding(it)
                    .fillMaxSize()
//                       .fillMaxWidth()
//                       .fillMaxHeight()
            /*.background(LightGrey)*/,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(7.dp))

            LazyColumn {
                if (notesList.isEmpty()){
                    items(count = dummyNotes.count(), itemContent = {item ->
//                        noteItem = dummyNotes[item]
//                        NoteCard(modifier, noteItem)
                        NoteCard(modifier, dummyNotes[item])
                    })
                } else {
                items(count = notesList.count(), itemContent = { item ->
//                    noteItem = notesList[item]
//                    NoteCard(modifier, noteItem)
                    NoteCard(modifier, notesList[item])
                })
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowNotesScreen() {
    val navController = rememberNavController()
    val menuStatus = remember { mutableStateOf(false) }
    val notesList = DummyNotes().loadNotes()
    CactusNotesTheme {
        NotesScreen(notesList = notesList, menuStatus = menuStatus, navController = navController)
    }
}