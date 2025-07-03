package com.hijakd.cactusnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.screens.CategoriesScreen
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.NewNoteScreen
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.screens.NotesScreen

@Composable
fun MainNavigation(noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel) {

    val menuStatus = remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val notesList = noteViewModel.notesList.collectAsState().value
    val categoryList = categoryViewModel.categoryList.collectAsState().value

    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name) {
        composable(ScreenRoutes.NotesScreen.name) {
            NotesScreen(
                modifier = Modifier,
                notesList,
                menuStatus,
                navController,
                onRemoveNote = { noteViewModel.removeNote(it) })
        }
        composable(ScreenRoutes.NewNoteScreen.name) {
            NewNoteScreen(
                modifier = Modifier,
                menuStatus,
                navController,
                onAddNote = { noteViewModel.addNote(it) })
        }
        composable(ScreenRoutes.CategoriesScreen.name) {
            CategoriesScreen(
                modifier = Modifier,
                categoryList,
                menuStatus,
                navController,
                onRemoveCategory = { categoryViewModel.removeCategory(it) },
                onAddCategory = { categoryViewModel.addCategory(it) })
        }
    }
}

