package com.hijakd.cactusnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hijakd.cactusnotes.navigation.NotesNavigation
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.NoteViewModel
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme

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
    val noteVModel = viewModel<NoteViewModel>()
    val categoryVModel = viewModel<CategoryViewModel>()
    NotesApp(noteViewModel = noteVModel, categoryViewModel = categoryVModel)
}

@Composable
fun NotesApp(modifier: Modifier = Modifier, noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel){
    NotesNavigation(noteViewModel,categoryViewModel)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CactusNotesTheme {
        Core()
    }
}