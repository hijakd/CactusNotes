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
import com.hijakd.cactusnotes.navigation.MainNavigation
import com.hijakd.cactusnotes.screens.CategoryViewModel
import com.hijakd.cactusnotes.screens.NoteViewModel
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
    val noteViewModel = viewModel<NoteViewModel>()
    val categoryViewModel = viewModel<CategoryViewModel>()
//    val notesList = viewModel<NoteViewModel>().notesList.collectAsState().value
//    val categoryList = viewModel<CategoryViewModel>().categoryList.collectAsState().value

    MainNavigation()
//    MainNavigation(noteViewModel, categoryViewModel)
//    MainNavigation(notesList = notesList, categoryList = categoryList)

//    MainApp(noteViewModel = noteViewModel, categoryViewModel = categoryViewModel)
}

//@Composable
//fun MainApp(noteViewModel: NoteViewModel, categoryViewModel: CategoryViewModel){
//    val notesList = noteViewModel.notesList.collectAsState().value
//    val categoryList = categoryViewModel.categoryList.collectAsState().value
//    MainNavigation(notesList = notesList, categoryList = categoryList)
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CactusNotesTheme {
        Core()
    }
}