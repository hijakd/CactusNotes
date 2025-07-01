package com.hijakd.cactusnotes.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.components.NoteButton
import com.hijakd.cactusnotes.components.NoteCard
import com.hijakd.cactusnotes.components.NoteInputText
import com.hijakd.cactusnotes.data.CategoryDefaults
import com.hijakd.cactusnotes.data.DummyNotes
import com.hijakd.cactusnotes.model.Categories
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.NeonGreen
import com.hijakd.cactusnotes.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier,
                notes: List<Note>,
                categories: List<Categories>,
                onRemoveNote: (Note) -> Unit,
                onAddNote: (Note) -> Unit,
                onAddCategory: (Categories) -> Unit) {

    val ctx = LocalContext.current
    var noteItem: Note
    val txtModifier = modifier.padding(top = 6.dp, bottom = 7.dp)
            .fillMaxWidth(0.9f)

    var canAddNewNote by remember { mutableStateOf(false) }
    var canAddCategory by remember { mutableStateOf(false) }
    var menuExpanded by remember { mutableStateOf(false) }
    var dropDownMenuItemSelected by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var noteCategory by remember { mutableStateOf("") }
    var newCategory by remember { mutableStateOf("") }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text("Notes") },
            actions = {
                Icon(
                    Icons.Rounded.Add, contentDescription = "Add note", modifier
                            .padding(end = 15.dp)
                            .size(37.dp)
                            .clickable { canAddNewNote = !canAddNewNote })
                Icon(
                    Icons.Rounded.MoreVert, contentDescription = "options menu", modifier
                            .padding(end = 12.dp)
                            .size(30.dp)
                            .clickable { TODO("add options menu") })
            },
            colors = topAppBarColors(containerColor = NeonGreen, titleContentColor = MaterialTheme.colorScheme.primary)
        )
    }) {
        Column(
            modifier
                    .padding(it)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(7.dp))

            if (canAddNewNote) {
                NoteInputText(
                    modifier = txtModifier,
                    text = title,
                    label = "Title",
                    singleLine = true,
                    onTextChange = { noteTitle ->
                        title = noteTitle
                    }
                )

                NoteInputText(
                    modifier = txtModifier,
                    text = body,
                    label = "Body",
                    onTextChange = { noteBody ->
                        body = noteBody
                    }
                )

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                    var expandDropDown by remember { mutableStateOf(false) }
                    val catsList: MutableList<Categories> = if (categories.isEmpty()) {
                        CategoryDefaults().loadCategories() as MutableList<Categories>
                    } else {
                        categories as MutableList<Categories>
                    }

                    /** TODO: arrange dropDownMenu save button in boxes **/

                    if (noteCategory.isNotEmpty()) {
                        Text(noteCategory)
                    } else {
                        Text("Category: ")
                    }

                    // DropDownMenu
                    Column(
                        modifier = Modifier
                                .padding(16.dp)
                                .background(Pink80), horizontalAlignment = Alignment.Start
                    ) {
                        IconButton(onClick = { expandDropDown = !expandDropDown }) {
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                contentDescription = "More options",
                                modifier.size(32.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = expandDropDown,
                            onDismissRequest = { expandDropDown = false },
                            offset = DpOffset(
                                x = (-70).dp,
                                y = 0.dp
                            )
                        ) {
                            for (category in catsList) {
                                DropdownMenuItem(
                                    text = {
                                        if (dropDownMenuItemSelected && category.category == noteCategory) {
                                            Text(
                                                text = category.category,
                                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Black)
                                            )
                                        } else {
                                            Text(text = category.category, style = MaterialTheme.typography.bodyMedium)
                                        }
                                    },
                                    modifier = modifier.background(
                                        if (dropDownMenuItemSelected && category.category == noteCategory) {
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                                        } else {
                                            Color.Unspecified
                                        }
                                    ),
                                    onClick = {
                                        noteCategory = category.category
                                        dropDownMenuItemSelected = !dropDownMenuItemSelected
                                        expandDropDown = !expandDropDown
                                    }
                                )
                            }
                        }
                    } // END of DropDownMenu

                    NoteButton(modifier = modifier, text = "Save") {
                        if (title.isNotEmpty()) {
                            onAddNote(Note(title = title, body = body, category = noteCategory))
                            Toast.makeText(ctx, "Note Added", Toast.LENGTH_SHORT).show()
                            // clear the display title & body inputFields after saving
                            title = ""
                            body = ""
                        }
                    }
                }

                HorizontalDivider(modifier.padding(horizontal = 10.dp, vertical = 10.dp))
            }

            if (!canAddCategory){
                NoteInputText(
                    txtModifier,
                    text = newCategory,
                    label = "Category",
                    singleLine = true,
                    onTextChange = { categoryName ->
                        newCategory = categoryName
                    }
                )

                NoteButton(text = "Save") {
                    if (newCategory.isNotEmpty() || !categories.equals(newCategory)) {
                        onAddCategory(Categories(category = newCategory))
                        Toast.makeText(ctx, "New Category added", Toast.LENGTH_SHORT).show()
                        // clear the display title & body inputFields after saving
                        newCategory = ""
                    }
                }
                HorizontalDivider(modifier.padding(horizontal = 10.dp, vertical = 10.dp))
            }

            LazyColumn {
                items(count = notes.count(), itemContent = { item ->
                    noteItem = notes[item]
                    NoteCard(modifier, noteItem)
                })
            } // END of LazyColumn
        } // END of "content" Column
    } // END of Scaffold
} // END of NotesScreen

@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    NotesScreen(
        notes = DummyNotes().loadNotes(),
        categories = CategoryDefaults().loadCategories(),
        onRemoveNote = {},
        onAddNote = {},
        onAddCategory = {}
    )
}