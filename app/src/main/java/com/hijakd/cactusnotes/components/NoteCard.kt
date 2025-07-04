package com.hijakd.cactusnotes.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hijakd.cactusnotes.database.DummyNotes
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.navigation.ScreenRoutes
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.ui.theme.DarkGrey
import com.hijakd.cactusnotes.ui.theme.Green80
import com.hijakd.cactusnotes.utils.formatDate

//@Preview(showBackground = true)
@Composable
fun NoteCard(modifier: Modifier = Modifier, note: Note, navController: NavController, onRemoveNote: (Note) -> Unit, onNoteClicked: (Note) -> Unit = {}) {
    val cornerClip = 13.dp
//    val dummyDate = Date.from(Instant.now())
    var expanded by remember { mutableStateOf(false) }
    val ctx = LocalContext.current

    Card(
        modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
                .clickable { onNoteClicked(note) },
        shape = RoundedCornerShape(cornerClip),
//        border = BorderStroke(Dp.Hairline, Black),
        elevation = CardDefaults.cardElevation(7.dp)
        ) {
        Surface(
                modifier
                        .clip(RoundedCornerShape(topEnd = cornerClip, bottomStart = cornerClip)),
                color = Green80,
                tonalElevation = 7.dp,
               ) {
            Column(
                    modifier
                            .clickable { onNoteClicked(note) }
                            .padding(7.dp), horizontalAlignment = Alignment.Start) {

                /* Show "Category" & "Date" */
                Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(note.category, style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic))
                    Text(
                            text = formatDate(note.timeStamp.time),
                            modifier.padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                } // END of Row

                /* Show note title and expansion icon */
                Row(
                        modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded }, horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                    Text(note.title, modifier, style = MaterialTheme.typography.titleMedium)
                    Icon(if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, "Down arrow")
                } // END of Row

                AnimatedVisibility(visible = expanded) {
                    Column {
                        /* body text */
                        Text(note.body, style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.size(5.dp))
                        /* edit/delete icons */
                        Row(
                            modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
//                            IconButton(onClick = {navController.navigate(route = ScreenRoutes.EditNoteScreen.name + "/${note}")}){
                            IconButton(onClick = {navController.navigate(route = ScreenRoutes.EditNoteScreen.name + "/${note.id}")}){
//                            IconButton(onClick = { Toast.makeText(ctx, "editableNote: ${note.id}", Toast.LENGTH_SHORT).show()}){
                                Icon(
                                    Icons.Rounded.Edit,
                                    contentDescription = "edit icon",
                                    modifier
                                            .size(20.dp)
                                            /*.clickable { TODO("add category edit") }*/,
//                                    .clickable { cardColor = Red },
                                    tint = DarkGrey
                                )
                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            IconButton(onClick = {onRemoveNote(note)}){
                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = "delete icon",
                                    modifier
                                            .size(20.dp),
                                    tint = DarkGrey
                                )
                            }
                            Spacer(modifier = Modifier.width(13.dp))
                        }
                    }




                }
            } // END of Column
        } // END of Surface
    } // END of Card
} // END of NoteCard


@Preview(showBackground = true)
@Composable
fun ShowNoteCard() {
    val navController = rememberNavController()
    val notesList = DummyNotes().loadNotes().first()
    CactusNotesTheme {
        NoteCard(note = notesList, navController = navController, onRemoveNote = {})
    }
}