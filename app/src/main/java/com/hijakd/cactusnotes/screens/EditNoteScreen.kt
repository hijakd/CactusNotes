package com.hijakd.cactusnotes.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Save
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.components.CategoriesDropDown
import com.hijakd.cactusnotes.components.NavDropDownMenu
import com.hijakd.cactusnotes.components.TextInput
import com.hijakd.cactusnotes.database.CategoryDefaults
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.navigation.ScreenRoutes
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.ui.theme.LightGrey
import com.hijakd.cactusnotes.utils.findNoteById
import java.time.Instant
import java.util.Date

/* TODO: fix this to receive/retrieve note properly */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(modifier: Modifier = Modifier,
//                   notesList: MutableList<Note>,
//                   noteId: String?,
                   menuStatus: MutableState<Boolean>,
                   navController: NavHostController,
                   onUpdateNote: (Note) -> Unit) {

    val ctx = LocalContext.current
    val txtModifier = modifier
            .padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)
//    val noteIndex = findNoteIndex(noteId, notesList)
    var noteIndex = 0



    val dropMenuItemSelected = remember { mutableStateOf(false) }
    val expandCategories = remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
//    var title by remember { mutableStateOf(notesList.get(noteIndex).title) }
    var body by remember { mutableStateOf("") }
//    var body by remember { mutableStateOf(notesList.get(noteIndex).body) }
    val category = remember { mutableStateOf("") }
//    val category = remember { mutableStateOf(notesList.get(noteIndex).category) }


//    while (notesList.get(noteIndex).id.toString() != noteId){
//        noteIndex++
//    }

        Scaffold(modifier.fillMaxSize(), topBar = {

//            Toast.makeText(ctx, "noteIndex: $noteIndex", Toast.LENGTH_SHORT).show()
//    Toast.makeText(ctx, notesList.get(noteIndex).title, Toast.LENGTH_SHORT).show()
        TopAppBar(
            title = { Text("Edit Note") },
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
                /* Save button */
                IconButton(onClick = {
                    onUpdateNote(
                        Note(
                            title = title,
                            body = body,
                            category = category.value,
                            editedTimeStamp = Date.from(Instant.now())
                        )
                    )
                    navController.navigate(route = ScreenRoutes.NotesScreen.name)
                }) {
                    Icon(
                        Icons.Rounded.Save,
                        contentDescription = "save item icon",
                        modifier
                                .padding(end = 15.dp)
                                .size(37.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                /* Categories button */
                IconButton(onClick = { expandCategories.value = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "category icon",
                        modifier
                                .padding(end = 15.dp)
                                .size(30.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                /* TODO: replace emptyList()/default list */
                CategoriesDropDown(
                    modifier, expandCategories, CategoryDefaults().loadCategories(),
                    menuItemSelected = dropMenuItemSelected,
                    noteCategory = category
                )
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
                    .fillMaxWidth()
                    .background(LightGrey),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(7.dp))
            TextInput(modifier = txtModifier, text = title, label = "Title", singleLine = true) { noteTitle ->
                title = noteTitle
            }
            TextInput(modifier = txtModifier, text = body, label = "Body", singleLine = false) { noteBody ->
                body = noteBody
            }
        }
    } // END of Scaffold
}

//@Preview(showBackground = true)
@Preview(showBackground = true)
@Composable
fun ShowEditNotesScreen() {
    val navController = rememberNavController()
    val menuStatus = remember { mutableStateOf(false) }
    CactusNotesTheme {
        NewNoteScreen(menuStatus = menuStatus, navController = navController, onAddNote = {})
    }
}

//fun findNoteIndex(noteId: String?, mutableNotes: MutableList<Note>): Int{
//    var idx = 0
////    for (notes in mutableNotes){
////        if(notes.id.toString() == noteId){
////            index = mutableNotes.indexOf(notes)
////        }
////    }
//
//    while (mutableNotes.get(idx).id.toString() != noteId){
//        idx++
//    }
//
//    return idx
//}