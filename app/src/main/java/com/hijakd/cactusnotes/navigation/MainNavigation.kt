package com.hijakd.cactusnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.database.DummyNotes
import com.hijakd.cactusnotes.model.Category
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.screens.CategoriesScreen
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.NewNoteScreen
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.screens.NotesScreen

@Composable
fun MainNavigation() {
//fun MainNavigation(notesList: List<Note>, categoryList: List<Category>) {
//fun MainNavigation(noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel) {

    val menuStatus = remember { mutableStateOf(false) }
    val navController = rememberNavController()

//    val noteViewModel = viewModel<NoteViewModel>()
//    val categoryViewModel = viewModel<CategoryViewModel>()
//    val notesList = noteViewModel.notesList.collectAsState().value
//    val categoryList = categoryViewModel.categoryList.collectAsState().value

    val notesList = viewModel<NoteViewModel>().notesList.collectAsState().value
    val categoryList = viewModel<CategoryViewModel>().categoryList.collectAsState().value

//    val categoryList = emptyList<Category>()
//    val categoryList = CategoryDefaults().loadCategories()


    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name){
//    NavHost(navController = navController, startDestination = ScreenRoutes.CategoriesScreen.name){
        composable(ScreenRoutes.NotesScreen.name) {
            NotesScreen(modifier = Modifier, notesList, menuStatus, navController)
//            NotesScreen(modifier = Modifier, notesList, menuStatus)
        }
        composable(ScreenRoutes.NewNoteScreen.name) {
            NewNoteScreen(modifier = Modifier, menuStatus, navController)
        }
        // TODO replace categories parameter with database entries
        composable(ScreenRoutes.CategoriesScreen.name) {
            CategoriesScreen(modifier = Modifier, categoryList, menuStatus, navController)
        }
    }
}

