package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.components.NavDropDownMenu
import com.hijakd.cactusnotes.components.SaveButton
import com.hijakd.cactusnotes.components.TextInput
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.Grey
import com.hijakd.cactusnotes.ui.theme.IceBlue
import com.hijakd.cactusnotes.ui.theme.LightGrey
import com.hijakd.cactusnotes.ui.theme.MathGreen
import com.hijakd.cactusnotes.ui.theme.NeonGreen
import com.hijakd.cactusnotes.ui.theme.PurpleGrey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val txtModifier = modifier
            .padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)

    val menuStatus = remember { mutableStateOf(false) }
    var canAddNewNote by remember { mutableStateOf(false) }
    var expandDropDown by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text("Notes") },
                  navigationIcon = {
                      IconButton(onClick = {menuStatus.value = true}){
                          Icon(
                              Icons.Rounded.Menu,
                              modifier = modifier
                                      .padding(horizontal = 10.dp),
                              contentDescription = "back arrow",
                              tint = MaterialTheme.colorScheme.onPrimary
                          )
                          NavDropDownMenu(menuStatus, navController)
                      }
                  },
                  actions = {
                      Icon(Icons.Rounded.Add,
                           contentDescription = "add new note",
                           modifier
                                   .padding(end = 15.dp)
                                   .size(37.dp)
                                   .clickable { canAddNewNote = !canAddNewNote },
                           tint = MaterialTheme.colorScheme.onPrimary)
                  },
                  colors = topAppBarColors(containerColor = MaterialTheme.colorScheme.primary,
                                           titleContentColor = MaterialTheme.colorScheme.onPrimary)) // END of TopAppBar
    }) {
        Column(modifier
                       .padding(it)
                       .fillMaxWidth()
                       .background(LightGrey),
               horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(7.dp))

            if (!canAddNewNote) {
                TextInput(modifier = txtModifier, text = title, label = "Title", singleLine = true) { noteTitle ->
                    title = noteTitle
                }

                TextInput(modifier = txtModifier, text = body, label = "Body") { noteBody ->
                    body = noteBody
                }

                Row(modifier.fillMaxWidth(0.9f).background(MathGreen),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(modifier.background(NeonGreen)/*.fillMaxWidth(0.3f)*/){
                        Text("Select Category")
                    }
                    Column(modifier.padding(horizontal = 3.dp)/*.fillMaxWidth(0.3f)*/.background(Grey)){
                        Text("DropDownMenu")
                    }
                    Column(modifier.background(IceBlue)/*.fillMaxWidth(0.3f)*/){
                        SaveButton { Note(title = title, body = body, category = category) }
                    }
                } // END Row

                HorizontalDivider(modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                                  thickness = 1.dp,
                                  color = PurpleGrey)
            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun ShowNotesScreen() {
    val navController = rememberNavController()
    NotesScreen(navController = navController)
}