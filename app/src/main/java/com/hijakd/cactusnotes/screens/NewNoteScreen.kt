package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hijakd.cactusnotes.components.TopBar

@Composable
fun NewNoteScreen(modifier: Modifier = Modifier, menuStatus: MutableState<Boolean>, navController: NavHostController) {
    val txtModifier = modifier
            .padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)

//    val menuStatus = remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopBar(modifier, "New Note", menuStatus, true, navController)
    }) {


    } // END of Scaffold
}