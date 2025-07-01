package com.hijakd.cactusnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.EditCategories
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.screens.NotesScreen

@Composable
fun NotesNavigation(noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel){
    val navController = rememberNavController()
    val notesList = noteViewModel.notesList.collectAsState().value
    val categoryList = categoryViewModel.catsList.collectAsState().value

    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name){
        composable(ScreenRoutes.NotesScreen.name) {
            NotesScreen(
                modifier = Modifier, navController = navController,
                notes = notesList,
                categories = categoryList,
                onRemoveNote = { noteViewModel.removeNote(it) },
                onAddNote = { noteViewModel.addNote(it) },
                onAddCategory = { categoryViewModel.addCategory(it) }
            )
        }
        composable(route = ScreenRoutes.EditCategories.name + "/{notes}", arguments = listOf(navArgument(name = "notes") {type =
            NavType.StringType})) {backStackEntry ->
//            EditCategories(navController = navController, backStackEntry.arguments?.getString("notes"))
            EditCategories(navController = navController)
        }
    }
}