package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.hijakd.cactusnotes.components.TextInput
import com.hijakd.cactusnotes.components.TopBar
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.ui.theme.LightGrey

@Composable
fun NewNoteScreen(modifier: Modifier = Modifier, menuStatus: MutableState<Boolean>, navController: NavHostController) {
    val txtModifier = modifier
            .padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)

    val dropMenuItemSelected = remember { mutableStateOf(false) }
    val category = remember { mutableStateOf("") }

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopBar(
            modifier = modifier,
            title = "New Note",
            menuStatus = menuStatus,
            navController = navController,
            saveIcon = true,
            editNote = true,
            dropMenuItemSelected,
            category
        )
    }) {
        Column(modifier.padding(it).fillMaxWidth().background(LightGrey),
               horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(7.dp))
            TextInput(modifier = txtModifier, text = title, label = "Title", singleLine = true) {noteTitle ->
                title = noteTitle
            }
            TextInput(modifier = txtModifier, text = body, label = "Body", singleLine = false) {noteBody ->
                body = noteBody
            }
        }
    } // END of Scaffold
}

//@Preview(showBackground = true)
@Preview(showBackground = true, )
@Composable
fun ShowNewNotesScreen() {
    val navController = rememberNavController()
    val menuStatus = remember { mutableStateOf(false) }
    CactusNotesTheme {
        NewNoteScreen( menuStatus = menuStatus, navController = navController)
    }
}