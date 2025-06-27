package com.hijakd.cactusnotes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
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
                  maxLine: Int = 1,
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
        maxLines = maxLine,
        shape = RoundedCornerShape(corners)
    )
}

//@Preview
//@Preview(showBackground = true)
@Composable
fun NoteCard(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit = {}){
    val cornerClip = 13.dp
    val dummyDate = Date.from(Instant.now())

    Surface(modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(topEnd = cornerClip, bottomStart = cornerClip))
                    .fillMaxWidth(0.95f),
            color = Green80,
            tonalElevation = 7.dp,
            /*shadowElevation = 17.dp*/) {
        Column(modifier
                       .clickable { /*onNoteClicked(note)*/ }
                       .padding(7.dp), horizontalAlignment = Alignment.Start) {

            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                Text("Category Blah", style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic))
                /*Spacer(modifier = Modifier.size(10.dp))*/
                Text(text = formatDate(dummyDate),modifier.padding(top = 3.dp), style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold ))
//            Text(text = formatDate(note.entryDate.time),modifier.padding(top = 3.dp), style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold ))

            } // END of Row
            
            Text("Title Blah", style = MaterialTheme.typography.titleMedium)
            Text("Body Blah", style = MaterialTheme.typography.bodyMedium)

        } // END of Column
    } // END of Surface
}