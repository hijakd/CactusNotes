package com.hijakd.cactusnotes.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.hijakd.cactusnotes.navigation.ScreenRoutes

@Composable
fun NavDropDownMenu(menuStatus: MutableState<Boolean>, navController: NavController){
    DropdownMenu(expanded = menuStatus.value, onDismissRequest = {menuStatus.value = false}) {
        DropdownMenuItem(text = {Text("Notes")}, onClick = {
            menuStatus.value = false
            navController.navigate(route = ScreenRoutes.NotesScreen.name)
        })
        DropdownMenuItem(text = { Text("New Note") }, onClick = {
                menuStatus.value = false
                navController.navigate(route = ScreenRoutes.NewNoteScreen.name)
            }
        )
        DropdownMenuItem(text = {Text("Categories")}, onClick = {
            menuStatus.value = false
            navController.navigate(route = ScreenRoutes.CategoriesScreen.name)
        })
    }
}