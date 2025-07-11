package com.hijakd.cactusnotes.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.util.convertByteToUUID
import com.hijakd.cactusnotes.model.Category
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.screens.CategoriesScreen
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.EditNoteScreen
import com.hijakd.cactusnotes.screens.NewNoteScreen
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.screens.NotesScreen
import com.hijakd.cactusnotes.utils.findNoteById
import com.hijakd.cactusnotes.utils.findNoteIndex

@Composable
fun MainNavigation(noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel) {
    val TAG = "nav"

    val navController = rememberNavController()

    val menuStatus = remember { mutableStateOf(false) }
    val expandOptions = remember { mutableStateOf(false) }
    val doLoadSampleNotes = remember { mutableStateOf(false) }
    val showSampleNotesDialog = remember { mutableStateOf(true) }
//    val notesList = remember { mutableListOf<Note>() }
    val notesList = remember { mutableStateListOf<Note>() }
    var mutableNote = remember { mutableStateListOf<Note>() }
    val categoryList = remember { mutableStateListOf<Category>() }
    val noteId = remember { mutableStateOf("") }
    val noteIndex = remember { mutableIntStateOf(0) }

    val notes = noteViewModel.notesList.collectAsState().value
//    val categories = categoryViewModel.categoryList.collectAsState().value

    if (notes.isNotEmpty()) {
        showSampleNotesDialog.value = false
        for (note in notes) {
            notesList.add(note)
        }
    }

    if (categoryViewModel.categoryList.collectAsState().value.isNotEmpty()) {
        for (category in categoryViewModel.categoryList.collectAsState().value) {
            categoryList.add(category)
        }
    }

    mutableNote = findNoteById(noteId, notesList)
//    DeleteDbContents(noteViewModel, TAG)
//    Log.d(TAG, "MainNavigation: notesList size: ${notesList.size}")
//    Log.d(TAG, "MainNavigation: deleted notesList, of size ${noteViewModel.notesList.collectAsState().value.count()}")

    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name) {
        composable(route = ScreenRoutes.NotesScreen.name) {
            NotesScreen(
                modifier = Modifier,
                notesList = notesList,
                editableNoteId = noteId,
                expandOptionsMenu = expandOptions,
                menuStatus = menuStatus,
                navController = navController,
                showSampleNotesDialogStatus = showSampleNotesDialog,
                doLoadSampleNotes = doLoadSampleNotes,
                onDeleteAllNotes = {noteViewModel.removeNote(it)},
                onRemoveNote = {
                    noteViewModel.removeNote(it)
                    notesList.remove(it)},
                onAddNote = { noteViewModel.addNote(it) })
        }
        composable(route = ScreenRoutes.NewNoteScreen.name) {
            NewNoteScreen(
                modifier = Modifier,
                menuStatus = menuStatus,
                navController = navController,
                onAddNote = { noteViewModel.addNote(it) })
        }
        composable(
            route = ScreenRoutes.EditNoteScreen.name + "/{noteId}",
                   arguments = listOf(navArgument(name = "noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            EditNoteScreen(
                modifier = Modifier,
//                editableNote = mutableNote[findNoteById(noteId.value, notesList)],
//                notesList = notesList,
//                editableNoteId = noteId,
                menuStatus = menuStatus,
                navController = navController,
                onUpdateNote = { noteViewModel.updateNote(it) })
        }
        composable(route = ScreenRoutes.CategoriesScreen.name) {
            CategoriesScreen(
                modifier = Modifier,
                categories = categoryList,
                menuStatus = menuStatus,
                navController = navController,
                onRemoveCategory = { categoryViewModel.removeCategory(it) },
                onAddCategory = { categoryViewModel.addCategory(it) })
        }
    }
}

@Composable
private fun DeleteDbContents(noteViewModel: NoteViewModel, TAG: String) {
    if (noteViewModel.notesList.collectAsState().value.isNotEmpty()) {
        Log.d(TAG, "MainNavigation: loading notesList, of size ${noteViewModel.notesList.collectAsState().value.count()}")
        for (note in noteViewModel.notesList.collectAsState().value) {
            noteViewModel.removeNote(note)
        }
    }
}

