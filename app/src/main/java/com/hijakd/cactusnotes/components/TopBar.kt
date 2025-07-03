package com.hijakd.cactusnotes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hijakd.cactusnotes.database.CategoryDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier,
           title: String,
           menuStatus: MutableState<Boolean>,
           navController: NavHostController,
           saveIcon: Boolean = false,
           editNote: Boolean = false,
           dropDownMenuItemSelected: MutableState<Boolean>,
           noteCategory: MutableState<String>) {

    val expandCategories = remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
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
            if (saveIcon) {
                Icon(
                    Icons.Rounded.Save,
                    contentDescription = "save item icon",
                    modifier
                            .padding(end = 15.dp)
                            .size(37.dp)
//                               .clickable { canAddNewNote = !canAddNewNote },
                            .clickable { TODO("add note saving") },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "add new item icon",
                    modifier
                            .padding(end = 15.dp)
                            .size(37.dp)
//                               .clickable { canAddNewNote = !canAddNewNote },
                            .clickable { TODO("add note saving") },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (editNote) {
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
//                      CategoriesDropDown(expandCategories, emptyList())
                CategoriesDropDown(
                    modifier, expandCategories, CategoryDefaults().loadCategories(),
                    menuItemSelected = dropDownMenuItemSelected,
                    noteCategory = noteCategory
                )
            }

        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}