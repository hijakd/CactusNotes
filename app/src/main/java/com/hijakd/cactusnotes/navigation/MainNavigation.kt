package com.hijakd.cactusnotes.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hijakd.cactusnotes.components.DefaultNotesDialog
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.screens.CategoriesScreen
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.EditNoteScreen
import com.hijakd.cactusnotes.screens.NewNoteScreen
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.screens.NotesScreen

@Composable
fun MainNavigation(noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel) {
    val TAG = "nav"

    val menuStatus = remember { mutableStateOf(false) }
    val showSampleNotesDialog = remember { mutableStateOf(true) }
    val doLoadSampleNotes = remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val notesList = noteViewModel.notesList.collectAsState().value
    val categoryList = categoryViewModel.categoryList.collectAsState().value

    val mutableNotes = remember { mutableListOf<Note>() }

//    for (note in notesList){
//        mutableNotes.add(note)
//    }

    if (noteViewModel.notesList.collectAsState().value.isNotEmpty()){
        for (note in noteViewModel.notesList.collectAsState().value) {
            mutableNotes.add(note)
        }
    }

    Log.d(TAG, "MainNavigation: $showSampleNotesDialog")

//    if (showSampleNotesDialog.value && mutableNotes.isEmpty()){
//        DefaultNotesDialog(loadSampleNotesStatus = doLoadSampleNotes, mutableNotesList = mutableNotes)
//        if (doLoadSampleNotes.value){
//            for (note in mutableNotes){
//                noteViewModel.addNote(note = note)
//            }
//        }
//        showSampleNotesDialog.value = false
//    }

    for (note in noteViewModel.notesList.collectAsState().value){
        mutableNotes.add(note)
    }

    NavHost(navController = navController, startDestination = ScreenRoutes.NotesScreen.name) {
        composable(route = ScreenRoutes.NotesScreen.name) {
            NotesScreen(
                modifier = Modifier,
//                notesList,
                notesList = mutableNotes,
                menuStatus = menuStatus,
                navController = navController,
                showSampleNotesDialog,
                doLoadSampleNotes,
                onRemoveNote = { noteViewModel.removeNote(it) },
            onAddNote = {noteViewModel.addNote(it)})
        }
        composable(route = ScreenRoutes.NewNoteScreen.name) {
            NewNoteScreen(
                modifier = Modifier,
                menuStatus,
                navController,
                onAddNote = { noteViewModel.addNote(it) })
        }
        composable(route = ScreenRoutes.EditNoteScreen.name + "/{noteId}",
                   arguments = listOf(navArgument(name = "noteId") {type = NavType.StringType})) {backStackEntry ->
            EditNoteScreen(
                modifier = Modifier,
//                note = findNoteById(backStackEntry.arguments!!.getString("noteId"), notesList),
//                notesList = mutableNotes,
//                notesList,
//                noteId = backStackEntry.arguments!!.getString("noteId"),
//                note = noteViewModel.getNote(noteId = backStackEntry.arguments?.getString("note")) as Note,
                menuStatus,
                navController,
                onUpdateNote = { noteViewModel.updateNote(it) })
        }
        composable(route = ScreenRoutes.CategoriesScreen.name) {
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

fun filterNotes(noteId: String?, mutableNotes: MutableList<Note>): Note{
    var index = 0
    for (notes in mutableNotes){
        if(notes.id.toString() == noteId){
            index = mutableNotes.indexOf(notes)
        }
    }
    return mutableNotes.get(index)
}