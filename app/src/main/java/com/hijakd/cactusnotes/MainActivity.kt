package com.hijakd.cactusnotes

import android.R.attr.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hijakd.cactusnotes.data.DummyNotes
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.screens.NotesScreen
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CactusNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Core(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Core(modifier: Modifier = Modifier) {
    /* don't pass this modifier along here, it will add padding 'all down the line' */
    // NotesScreen(notes = DummyNotes().loadNotes(), onRemoveNote = {}, onAddNote = {})
    var firstLoad: Boolean = true

    val noteViewModel = viewModel<NoteViewModel>()
    NotesApp(noteViewModel = noteViewModel)
//    NotesApp(noteViewModel = noteViewModel, firstLoad = firstLoad)
    firstLoad = false
}

@Composable
fun NotesApp(modifier: Modifier = Modifier, noteViewModel: NoteViewModel){
//fun NotesApp(modifier: Modifier = Modifier, noteViewModel: NoteViewModel, firstLoad: Boolean){

    /*
    if (firstLoad){
        NotesScreen(notes = DummyNotes().loadNotes(), onRemoveNote = {noteViewModel.removeNote(it)}, onAddNote = {noteViewModel.addNote(it)})
    } else {
        val notesList = noteViewModel.notesList.collectAsState().value
        NotesScreen(modifier, notesList, onRemoveNote = {noteViewModel.removeNote(it)}, onAddNote = {noteViewModel.addNote(it)})
    }
    */

    val notesList = noteViewModel.notesList.collectAsState().value
    NotesScreen(modifier, notesList, onRemoveNote = {noteViewModel.removeNote(it)}, onAddNote = {noteViewModel.addNote(it)})

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CactusNotesTheme {
        Core()
    }
}