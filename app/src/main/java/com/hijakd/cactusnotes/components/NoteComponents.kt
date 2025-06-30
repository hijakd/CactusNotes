package com.hijakd.cactusnotes.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.Green80
import com.hijakd.cactusnotes.utils.formatDate
import java.time.Instant
import java.util.Date


@Composable
fun NoteInputText(modifier: Modifier = Modifier,
                  text: String,
                  label: String,
                  singleLine: Boolean = false,
                  onTextChange: (String) -> Unit,
                  onImeAction: () -> Unit = {}) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val corners = 13.dp

    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.clip(RoundedCornerShape(corners)),
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        singleLine = singleLine,
        shape = RoundedCornerShape(corners)
    )
}

//@Preview
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
                        text = formatDate(note.timestamp.time),
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

@Composable
fun NoteButton(modifier: Modifier = Modifier, text: String, enabled: Boolean = true, onClick: () -> Unit) {
    Button(onClick = onClick, shape = CircleShape, enabled = enabled, modifier = modifier) {
        Text(text)
    }
}
