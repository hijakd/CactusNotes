package com.hijakd.cactusnotes.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.Green80
import com.hijakd.cactusnotes.utils.formatDate
import java.time.Instant
import java.util.Date

//@Preview(showBackground = true)
@Composable
fun NoteCard(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit = {}) {
    val cornerClip = 13.dp
    val dummyDate = Date.from(Instant.now())
    var expanded by remember { mutableStateOf(false) }

    Card(
            modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .fillMaxWidth()
                    .clickable { onNoteClicked(note) },
            shape = RoundedCornerShape(cornerClip),
            /*border = BorderStroke(Dp.Hairline, Black),*/
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

                // Show "Category" & "Date"
                Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(note.category, style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic))
                    Text(
                            text = formatDate(note.timeStamp.time),
                            modifier.padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                } // END of Row

                // Show note title and expansion icon
                Row(
                        modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded }, horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                    Text(note.title, modifier, style = MaterialTheme.typography.titleMedium)
                    Icon(if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, "Down arrow")
                } // END of Row
                AnimatedVisibility(visible = expanded) { Text(note.body, style = MaterialTheme.typography.bodyMedium) }
            } // END of Column
        } // END of Surface
    } // END of Card
} // END of NoteCard