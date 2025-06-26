package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.data.DummyNotes
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.NeonGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier, notes: List<Note>, onRemoveNote: (Note) -> Unit, onAddNote: (Note) -> Unit) {

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    Column(modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Notes") }, colors = TopAppBarDefaults.topAppBarColors(NeonGreen)) // END of TopAppBar

        Column(modifier.fillMaxWidth()) {
            val txtModifier = modifier.padding(top = 9.dp, bottom = 8.dp)

            Text("placeholder for note title input")
            Text("placeholder for note body input")

            HorizontalDivider(modifier.padding(horizontal = 10.dp, vertical = 15.dp))

        } // END of "content" Column

    } // END of outer Column

}

@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    NotesScreen(notes = DummyNotes().loadNotes(), onRemoveNote = {}, onAddNote = {})
}