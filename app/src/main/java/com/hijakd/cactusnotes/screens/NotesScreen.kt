package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.hijakd.cactusnotes.components.SaveButton
import com.hijakd.cactusnotes.components.TextInput
import com.hijakd.cactusnotes.components.TopBar
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.ui.theme.Grey
import com.hijakd.cactusnotes.ui.theme.IceBlue
import com.hijakd.cactusnotes.ui.theme.LightGrey
import com.hijakd.cactusnotes.ui.theme.MathGreen
import com.hijakd.cactusnotes.ui.theme.NeonGreen
import com.hijakd.cactusnotes.ui.theme.PurpleGrey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier, menuStatus: MutableState<Boolean>, navController: NavHostController) {

    val txtModifier = modifier
            .padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)

    val dropMenuItemSelected = remember { mutableStateOf(false) }
    val category = remember { mutableStateOf("") }

    var canAddNewNote by remember { mutableStateOf(false) }
    var expandDropDown by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopBar(modifier,
               title = "Notes",
               menuStatus = menuStatus,
               navController = navController,
               saveIcon = false,
               editNote = false,
               dropMenuItemSelected,
               category
        )
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
                    Column(modifier.background(NeonGreen)){
                        Text("Select Category")
                    }
                    Column(modifier.padding(horizontal = 3.dp).background(Grey)){
                        Text("DropDownMenu")
                    }
                    Column(modifier.background(IceBlue)){
                        SaveButton { Note(title = title, body = body, category = category.value) }
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
    val menuStatus = remember { mutableStateOf(false) }
    CactusNotesTheme {
        NotesScreen( menuStatus = menuStatus, navController = navController)
    }
}