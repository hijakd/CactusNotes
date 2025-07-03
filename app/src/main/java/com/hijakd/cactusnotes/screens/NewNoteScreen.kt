package com.hijakd.cactusnotes.screens

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(modifier: Modifier = Modifier,
                  menuStatus: MutableState<Boolean>,
                  navController: NavHostController,
                  onAddNote: (Note) -> Unit) {
    val txtModifier = modifier
            .padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)

    val dropMenuItemSelected = remember { mutableStateOf(false) }
    val expandCategories = remember { mutableStateOf(false) }
    val category = remember { mutableStateOf("") }

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        /*TopBar(
            modifier = modifier,
            title = "New Note",
            menuStatus = menuStatus,
            navController = navController,
            saveIcon = true,
            editNote = true,
            dropMenuItemSelected,
            category,
            saveNote = {onAddNote}
        )*/

        TopAppBar(
            title = { Text("New Note") },
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
                    onAddNote(Note(title = title, body = body, category = category.value))
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
fun ShowNewNotesScreen() {
    val navController = rememberNavController()
    val menuStatus = remember { mutableStateOf(false) }
    CactusNotesTheme {
        NewNoteScreen(menuStatus = menuStatus, navController = navController, onAddNote = {})
    }
}