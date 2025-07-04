package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
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
import com.hijakd.cactusnotes.components.NavDropDownMenu
import com.hijakd.cactusnotes.components.NoteCard
import com.hijakd.cactusnotes.database.DummyNotes
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.navigation.ScreenRoutes
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier,
                notesList: List<Note>,
                menuStatus: MutableState<Boolean>,
                navController: NavHostController,
                onRemoveNote: (Note) -> Unit,
                ) {

    val dummyNotes: List<Note> = DummyNotes().loadNotes()

    var canAddNewNote by remember { mutableStateOf(false) }
    var expandDropDown by remember { mutableStateOf(false) }
    var noteItem: Note

//    var title by remember { mutableStateOf("") }
//    var body by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        /*TopBar(
            modifier,
            title = "Notes",
            menuStatus = menuStatus,
            navController = navController,
            saveIcon = false,
            editNote = false,
            dropMenuItemSelected,
            category
        )*/
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
            Spacer(modifier = Modifier.size(7.dp))

            LazyColumn {
                if (notesList.isEmpty()) {
                    items(count = dummyNotes.count(), itemContent = { item ->
                        NoteCard(modifier, dummyNotes[item], navController, onRemoveNote = {})
                    })
                } else {
                    items(count = notesList.count(), itemContent = { item ->
                        noteItem = notesList[item]
                        NoteCard(modifier, noteItem, navController, onRemoveNote = {})
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
        NotesScreen(notesList = notesList, menuStatus = menuStatus, navController = navController, onRemoveNote = {})
    }
}