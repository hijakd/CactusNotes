package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    var canAddNewNote by remember { mutableStateOf(false) }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text("Notes") },
                  actions = {
                      Icon(Icons.Rounded.Add,
                           contentDescription = "add new note",
                           modifier
                                   .padding(end = 15.dp)
                                   .size(37.dp)
                                   .clickable { canAddNewNote = !canAddNewNote },
                           tint = MaterialTheme.colorScheme.onPrimary)
                  },
                  colors = topAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.onPrimary)) // END of TopAppBar
    }) {
        Column(modifier.padding(it)) {
            Text("Blah")
        }
    }


}


@Preview(showBackground = true)
@Composable
fun ShowNotesScreen() {
    val navController = rememberNavController()
    NotesScreen(navController = navController)
}