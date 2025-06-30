package com.hijakd.cactusnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hijakd.cactusnotes.screens.CategoryViewModel
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
    val noteViewModel = viewModel<NoteViewModel>()
    val catsViewModel = viewModel<CategoryViewModel>()
    NotesApp(noteViewModel = noteViewModel, categoryViewModel = catsViewModel)
}

@Composable
fun NotesApp(modifier: Modifier = Modifier, noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel) {
    val notesList = noteViewModel.notesList.collectAsState().value
    val categoryList = categoryViewModel.catsList.collectAsState().value
    NotesScreen(
        notes = notesList,
        categories = categoryList,
        onRemoveNote = { noteViewModel.removeNote(it) },
        onAddNote = { noteViewModel.addNote(it) },
        onAddCategory = { categoryViewModel.addCategory(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CactusNotesTheme {
        Core()
    }
}